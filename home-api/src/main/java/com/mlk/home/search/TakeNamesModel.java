package com.mlk.home.search;

import com.mlk.home.base.BaseEntity;
import com.mlk.home.base.BaseSearchModel;

import java.io.Serializable;

/**
 * Created by malikai on 2018-7-9.
 */
public class TakeNamesModel extends BaseSearchModel implements Serializable {
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
