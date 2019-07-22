package com.mlk.home.mapper;


import com.github.pagehelper.Page;
import com.mlk.home.entity.ManagerConfig;
import com.mlk.home.search.ManagerConfigModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ManagerConfigMapper extends Mapper<ManagerConfig> {
    ManagerConfig queryByConfigKey(@Param("configKey") String configKey);

    Page<ManagerConfig> queryManagerConfigByPage(ManagerConfigModel model);
}