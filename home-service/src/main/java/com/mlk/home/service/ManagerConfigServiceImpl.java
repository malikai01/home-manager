package com.mlk.home.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mlk.home.entity.ManagerConfig;
import com.mlk.home.mapper.ManagerConfigMapper;
import com.mlk.home.page.PageInfo;
import com.mlk.home.search.ManagerConfigModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author malikai
 * @version 1.0
 * @date 2019-7-22 15:02
 */
@Transactional
@Service(protocol = {"dubbo"}, timeout = 60000)
public class ManagerConfigServiceImpl implements ManagerConfigService {
    @Autowired
    private ManagerConfigMapper managerConfigMapper;

    @Override
    public ManagerConfig queryByConfigKey(String configKey) {
        return managerConfigMapper.queryByConfigKey(configKey);
    }

    @Override
    public boolean editManagerConfig(ManagerConfig managerConfig) {
        return managerConfigMapper.updateByPrimaryKeySelective(managerConfig) > 0;
    }

    @Override
    public boolean addManagerConfig(ManagerConfig managerConfig) {
        return managerConfigMapper.insertSelective(managerConfig) > 0;
    }

    @Override
    public PageInfo<ManagerConfig> queryManagerConfig(ManagerConfigModel model) {
        PageHelper.startPage(model.getPage(),model.getRows(),true);
        Page<ManagerConfig> page = managerConfigMapper.queryManagerConfigByPage(model);

        return new PageInfo<>(model.getPage(),model.getRows(),page.getTotal(),page.getResult());
    }
}
