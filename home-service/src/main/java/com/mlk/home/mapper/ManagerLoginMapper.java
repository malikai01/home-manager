package com.mlk.home.mapper;

import com.mlk.home.entity.ManagerLogin;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * Created by malikai on 2018-5-22.
 */
public interface ManagerLoginMapper extends BaseMapper<ManagerLogin>{
    ManagerLogin login(@Param("loginName") String loginName, @Param("password") String password);
}
