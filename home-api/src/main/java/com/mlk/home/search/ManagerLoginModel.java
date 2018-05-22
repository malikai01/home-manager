package com.mlk.home.search;

import com.mlk.home.entity.ManagerFamilyGroup;

import java.io.Serializable;
import java.util.List;

/**
 * Created by malikai on 2018-5-22.
 */
public class ManagerLoginModel implements Serializable{
    private String loginName;
    private String password;
    private List<ManagerFamilyGroup> groups;

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

    public List<ManagerFamilyGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<ManagerFamilyGroup> groups) {
        this.groups = groups;
    }
}
