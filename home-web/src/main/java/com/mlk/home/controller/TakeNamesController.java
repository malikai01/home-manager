package com.mlk.home.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlk.home.entity.TakeNames;
import com.mlk.home.search.TakeNamesModel;
import com.mlk.home.service.TakeNamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by malikai on 2018-7-9.
 */
@Controller
@RequestMapping("v1/takeNames")
public class TakeNamesController {
    @Reference
    private TakeNamesService takeNamesService;
    @RequestMapping("/add")
    public boolean addNames(@RequestBody TakeNames names){
        return takeNamesService.addNnames(names);
    }
    @RequestMapping("/query")
    public TakeNames queryNames(@RequestBody TakeNamesModel model){
        return takeNamesService.queryNames(model);
    }
}
