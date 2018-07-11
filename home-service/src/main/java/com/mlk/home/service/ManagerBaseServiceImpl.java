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
    public Boolean binding(ManagerFamilyGroup group) {
        group.setCreateTime(new Date());
        group.setIsDelete("0");
        int result=0;
        if(EmptyUtils.isNotEmpty(group.getId())){
            group.setUpdateTime(new Date());
            result = managerFamilyGroupMapper.updateByPrimaryKey(group);
        }else {
             result = managerFamilyGroupMapper.insert(group);
        }
        return result>0;
    }

    @Override
    public Boolean cancelBinding(Long id) {
        int result = managerFamilyGroupMapper.cancelBinding(id);
        return result>0;
    }

    @Override
    public List<ManagerFamilyGroup> queryByLoginId(Long loginId) {
        return managerFamilyGroupMapper.queryByLoginId(loginId);
    }

    @Override
    public ManagerLogin login(ManagerLogin model) {
        return managerLoginMapper.login(model.getLoginName(),model.getPassword());
    }
}
