package com.school.schoolDemo.utils;

import com.school.schoolDemo.entity.enums.Gender;

public class StringToEnums {

    public static Gender stringToGender(String value){
        switch (value){
            case "male": return Gender.MALE;

            case "female": return Gender.FEMALE;

            case "others": return Gender.OTHERS;

            default: throw new AssertionError("Unknown Gender " + value);
        }
    }
}
