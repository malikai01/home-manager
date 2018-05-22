package com.mlk.home.service;

import com.mlk.home.entity.ManagerFamilyGroup;
import com.mlk.home.entity.ManagerLogin;

import java.util.List;

/**
 * Created by malikai on 2018-5-22.
 */
public interface ManagerBaseService {
    Long register(ManagerLogin model);

    Boolean binding(List<ManagerFamilyGroup> list);

    Boolean cancelBinding(Long id);
}
