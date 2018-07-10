package com.mlk.home.mapper;

import com.github.pagehelper.Page;
import com.mlk.home.entity.TakeNames;
import com.mlk.home.search.TakeNamesModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TakeNamesMapper extends Mapper<TakeNames> {
    Page<TakeNames> queryNames(TakeNamesModel model);

    TakeNames queryById(@Param("id") Integer cId);
}