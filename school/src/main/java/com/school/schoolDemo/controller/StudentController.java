package com.school.schoolDemo.controller;

import com.school.schoolDemo.entity.Student;
import com.school.schoolDemo.exceptions.ResourceNotFoundException;
import com.school.schoolDemo.payload.StudentDTO;
import com.school.schoolDemo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    @Tag(name = "POST")
    @Operation(description = "To save details of a new student")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentDTO studentDTO){
        return new ResponseEntity<>(studentService.saveStudent(studentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getStudents")
    @Tag(name = "GET")
    @Operation(description = "List all students")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<Student>> listStudents(){
        return new ResponseEntity<>(studentService.listStudents(), HttpStatus.OK);
    }

    @GetMapping("/getStudentById/{id}")
    @Tag(name = "GET")
    @Operation(description = "Get details of a student by Id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(studentService.listStudentById(id), HttpStatus.OK);
    }

    @PostMapping("/updateStudent/{id}")
    @Tag(name = "POST")
    @Operation(description = "Updates details of an existing student")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Student> updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(studentService.updateStudent(studentDTO, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    @Tag(name = "DELETE")
    @Operation(description = "Delete a student profile")
    @SecurityRequirement(name = "Bearer Authentication")
    public String deleteStudentById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return studentService.deleteStudentById(id);
    }
}
