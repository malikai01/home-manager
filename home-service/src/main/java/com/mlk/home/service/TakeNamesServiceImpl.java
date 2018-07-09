package com.mlk.home.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.mlk.home.entity.TakeNames;
import com.mlk.home.mapper.TakeNamesMapper;
import com.mlk.home.search.TakeNamesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by malikai on 2018-7-9.
 */
@Service(protocol = {" dubbo "},timeout = 6000)
@Transactional
public class TakeNamesServiceImpl implements TakeNamesService {
    @Autowired
    private TakeNamesMapper takeNamesMapper;
    @Override
    public boolean addNnames(TakeNames names) {
        return takeNamesMapper.insert(names)>0;
    }

    @Override
    public TakeNames queryNames(TakeNamesModel model) {
        return takeNamesMapper.queryNames(model);
    }
}
