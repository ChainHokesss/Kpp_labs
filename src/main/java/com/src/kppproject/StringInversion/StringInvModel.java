package com.src.kppproject.StringInversion;

import javax.persistence.*;

@Entity
@Table(name = "string_inversion")
public class StringInvModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String string_value1;
    private String string_value2;

    public StringInvModel() {
    }

    public StringInvModel(String string_value1, String string_value2) {
        this.string_value1 = string_value1;
        this.string_value2 = string_value2;
    }

    public StringInvModel(Integer id, String string_value1, String string_value2) {
        this.id = id;
        this.string_value1 = string_value1;
        this.string_value2 = string_value2;
    }

    public String getString_value1() {
        return string_value1;
    }

    public void setString_value1(String string_value1) {
        this.string_value1 = string_value1;
    }

    public String getString_value2() {
        return string_value2;
    }

    public void setString_value2(String string_value2) {
        this.string_value2 = string_value2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
