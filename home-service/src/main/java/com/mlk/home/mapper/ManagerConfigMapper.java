package com.mlk.home.mapper;


import com.github.pagehelper.Page;
import com.mlk.home.base.BaseSearchModel;
import com.mlk.home.entity.ManagerConfig;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ManagerConfigMapper extends Mapper<ManagerConfig> {
    ManagerConfig queryByConfigKey(@Param("configKey") String configKey);

    Page<ManagerConfig> queryManagerConfigByPage(BaseSearchModel model);
}