package com.mlk.home.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mlk.home.entity.TakeNames;
import com.mlk.home.mapper.TakeNamesMapper;
import com.mlk.home.page.PageInfo;
import com.mlk.home.search.TakeNamesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by malikai on 2018-7-9.
 */
@Service(protocol = {"dubbo"},timeout = 6000)
@Transactional
public class TakeNamesServiceImpl implements TakeNamesService {
    @Autowired
    private TakeNamesMapper takeNamesMapper;
    @Override
    public boolean addNnames(TakeNames names) {
        return takeNamesMapper.insert(names)>0;
    }

    @Override
    public PageInfo<TakeNames> queryNames(TakeNamesModel model) {
        PageHelper.startPage(model.getPage(),model.getRows(),true);
        Page<TakeNames> list = takeNamesMapper.queryNames(model);
        PageInfo pageInfo = new PageInfo<>(model.getPage(),model.getRows(),list.getTotal(),list.getResult());
        return pageInfo;
    }

    @Override
    public TakeNames queryById(Integer cId) {
        return takeNamesMapper.queryById(cId);
    }

    @Override
    public boolean editNnames(TakeNames names) {
        return takeNamesMapper.updateByPrimaryKeySelective(names)>0;
    }
}
