package com.mlk.home.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.mlk.home.common.utils.EmptyUtils;
import com.mlk.home.common.utils.MD5Util;
import com.mlk.home.entity.ManagerFamilyGroup;
import com.mlk.home.entity.ManagerLogin;
import com.mlk.home.mapper.ManagerFamilyGroupMapper;
import com.mlk.home.mapper.ManagerLoginMapper;
import com.mlk.home.search.ManagerLoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    public Long register(ManagerLogin model) {

        model.setPassword(MD5Util.getMD5(model.getPassword()));
        model.setRegisterTime(new Date());
        model.setIsDelete("0");
        managerLoginMapper.insert(model);
        return model.getId();
    }

    @Override
    public Boolean binding(List<ManagerFamilyGroup> list) {
        for (ManagerFamilyGroup group :
                list) {
            group.setCreateTime(new Date());
            group.setIsDelete("0");
            managerFamilyGroupMapper.insert(group);
        }
        return true;
    }

    @Override
    public Boolean cancelBinding(Long id) {
        return null;
    }
}
