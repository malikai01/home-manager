package com.mlk.home.search;

import com.mlk.home.base.BaseEntity;

/**
 * Created by malikai on 2018-7-9.
 */
public class TakeNamesModel extends BaseEntity{
    private Integer familyId;
    private String type;
    private String name;

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
