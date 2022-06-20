package com.school.schoolDemo.service;

import com.school.schoolDemo.entity.Student;
import com.school.schoolDemo.entity.enums.Gender;
import com.school.schoolDemo.exceptions.ResourceNotFoundException;
import com.school.schoolDemo.payload.StudentDTO;
import com.school.schoolDemo.repository.StudentRepository;
import com.school.schoolDemo.utils.StringToEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(StudentDTO studentDTO){

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setDob(dateFormat(studentDTO.getDob()));
        student.setAge(calculateAge(dateFormat(studentDTO.getDob())));
        student.setGender(StringToEnums.stringToGender(studentDTO.getGender()));

        return studentRepository.save(student);

    }

    public List<Student> listStudents(){
        return studentRepository.findAll();
    }

    public Student listStudentById(Long id) throws ResourceNotFoundException {
        if(studentRepository.existsById(id)){
            return studentRepository.findById(id).get();
        }
        else throw new ResourceNotFoundException("Student with id " + id + " does not exist!");
    }

    public Student updateStudent(StudentDTO studentDTO, Long id) throws ResourceNotFoundException {
        if(studentRepository.existsById(id)){
            Student student = listStudentById(id);
            student.setName(studentDTO.getName());
            student.setDob(dateFormat(studentDTO.getDob()));
            student.setAge(calculateAge(dateFormat(studentDTO.getDob())));
            student.setGender(StringToEnums.stringToGender(studentDTO.getGender()));

            return studentRepository.save(student);
        }
        else return saveStudent(studentDTO);
    }

    public String deleteStudentById(Long id) throws ResourceNotFoundException {
        studentRepository.delete(listStudentById(id));
        return "Student with id " + id + " deleted";
    }

    private LocalDate dateFormat(String inputDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(inputDate, formatter);
    }

    private Integer calculateAge(LocalDate dob){
        LocalDate date = LocalDate.now();
        if(dob != null){
            return Period.between(dob, date).getYears();
        }
        else return 0;
    }
}
