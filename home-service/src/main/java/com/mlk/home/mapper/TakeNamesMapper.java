package com.mlk.home.mapper;

import com.mlk.home.entity.TakeNames;
import com.mlk.home.search.TakeNamesModel;
import tk.mybatis.mapper.common.Mapper;

public interface TakeNamesMapper extends Mapper<TakeNames> {
    TakeNames queryNames(TakeNamesModel model);
}