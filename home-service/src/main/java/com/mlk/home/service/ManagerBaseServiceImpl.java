package com.mlk.home.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.mlk.home.common.utils.MD5Util;
import com.mlk.home.entity.ManagerFamilyGroup;
import com.mlk.home.entity.ManagerLogin;
import com.mlk.home.mapper.ManagerFamilyGroupMapper;
import com.mlk.home.mapper.ManagerLoginMapper;
import com.mlk.home.search.ManagerLoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by malikai on 2018-5-22.
 */
@Transactional
@Service(protocol = { "dubbo" }, timeout = 60000)
public class ManagerBaseServiceImpl implements ManagerBaseService {
    @Autowired
    private ManagerFamilyGroupMapper managerFamilyGroupMapper;
    @Autowired
    private ManagerLoginMapper managerLoginMapper;

    @Override
    //@Transactional
    public Boolean register(ManagerLoginModel model) {
        ManagerLogin login = new ManagerLogin();
        ManagerFamilyGroup group = new ManagerFamilyGroup();
        login.setLoginName(model.getLoginName());
        login.setPassword(MD5Util.getMD5(model.getPassword()));
        login.setRegisterTime(new Date());
        login.setIsDelete("0");
        group.setAge(model.getAge());
        group.setCreateTime(new Date());
        group.setName(model.getName());
        group.setIsDelete("0");
        group.setRelation(model.getRelation());
        managerLoginMapper.insert(login);
        group.setLoginId(login.getId());
        managerFamilyGroupMapper.insert(group);

        return true;
    }
}
