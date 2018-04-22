package com.mlk.home.search;

import com.mlk.home.base.BaseSearchModel;

/**
 * Created by malikai on 2018-4-18.
 */
public class StudentSearchModel extends BaseSearchModel{
    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
