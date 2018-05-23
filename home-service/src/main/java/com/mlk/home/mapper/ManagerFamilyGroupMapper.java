package com.mlk.home.mapper;

import com.mlk.home.entity.ManagerFamilyGroup;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * Created by malikai on 2018-5-22.
 */
public interface ManagerFamilyGroupMapper extends BaseMapper<ManagerFamilyGroup> {
    List<ManagerFamilyGroup> queryByLoginId(@Param("loginId") Long loginId);

    int cancelBinding(@Param("id") Long id);
}
