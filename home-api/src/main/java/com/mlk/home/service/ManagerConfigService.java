package com.mlk.home.service;


import com.mlk.home.base.BaseSearchModel;
import com.mlk.home.entity.ManagerConfig;
import com.mlk.home.page.PageInfo;

/**
 * Created by malikai on 2018-7-9.
 */
public interface ManagerConfigService {

    ManagerConfig queryByConfigKey(String configKey);

    boolean editManagerConfig(ManagerConfig managerConfig);

    boolean addManagerConfig(ManagerConfig managerConfig);

    PageInfo<ManagerConfig> queryManagerConfig(BaseSearchModel model);
}
