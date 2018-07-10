package com.mlk.home.service;

import com.mlk.home.entity.TakeNames;
import com.mlk.home.page.PageInfo;
import com.mlk.home.search.TakeNamesModel;

/**
 * Created by malikai on 2018-7-9.
 */
public interface TakeNamesService {
    boolean addNnames(TakeNames names);

    PageInfo<TakeNames> queryNames(TakeNamesModel model);
}
