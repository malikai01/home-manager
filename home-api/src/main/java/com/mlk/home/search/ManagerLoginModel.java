package com.mlk.home.search;

import java.io.Serializable;

/**
 * Created by malikai on 2018-5-22.
 */
public class ManagerLoginModel implements Serializable{
    private String loginName;
    private String password;
    private String name;
    private Integer age;
    private String relation;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
