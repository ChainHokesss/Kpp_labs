package com.src.kppproject.StringInversion.dto;

import com.src.kppproject.StringInversion.StringInvModel;

public class StringInverseDto {
    public String getString_value2() {
        return string_value2;
    }

    public void setString_value2(String string_value2) {
        this.string_value2 = string_value2;
    }

    public String getString_value1() {
        return string_value1;
    }

    public void setString_value1(String string_value1) {
        this.string_value1 = string_value1;
    }

    public StringInverseDto() {
    }

    public StringInverseDto(StringInvModel stringInvModel) {
        this.string_value1 = stringInvModel.getString_value1();
        this.string_value2 = stringInvModel.getString_value2();
    }

    public StringInverseDto(String string_value_1, String string_value_2) {
        this.string_value1 = string_value_1;
        this.string_value2 = string_value_2;
    }

    private String string_value1;
    private String string_value2;
}
