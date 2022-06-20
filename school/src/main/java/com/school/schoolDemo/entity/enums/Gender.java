package com.school.schoolDemo.entity.enums;

public enum Gender {
    MALE("male"),
    FEMALE("female"),
    OTHERS("others");

    private String code;

    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
