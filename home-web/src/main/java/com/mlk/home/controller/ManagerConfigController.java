package com.mlk.home.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlk.home.annotation.NeedAuthority;
import com.mlk.home.entity.ManagerConfig;
import com.mlk.home.page.PageInfo;
import com.mlk.home.search.ManagerConfigModel;
import com.mlk.home.service.ManagerConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by malikai on 2018-7-9.
 */
@Controller
@RequestMapping("v1/config")
public class ManagerConfigController {
    @Reference
    private ManagerConfigService managerConfigService;

    @RequestMapping("/queryHtml")
    public String queryHtml() {
        return "config/config_list";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    @NeedAuthority
    public PageInfo<ManagerConfig> queryManagerConfig(ManagerConfigModel model) {
        return managerConfigService.queryManagerConfig(model);
    }
}
