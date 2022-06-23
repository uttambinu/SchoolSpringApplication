package com.school.schoolDemo.utils;

import com.school.schoolDemo.entity.enums.Gender;

public class StringToEnums {

    public static Gender stringToGender(String value){
        switch (value.toLowerCase()){
            case "male":

            case "0":

            case "m":
                return Gender.MALE;

            case "female":

            case "1":

            case "f":
                return Gender.FEMALE;

            case "others":

            case "o":
                return Gender.OTHERS;

            default: throw new AssertionError("Unknown Gender " + value);
        }
    }
}
