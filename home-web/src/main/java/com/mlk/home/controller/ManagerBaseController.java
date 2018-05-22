package com.mlk.home.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlk.home.common.utils.DataResult;
import com.mlk.home.search.ManagerLoginModel;
import com.mlk.home.service.ManagerBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by malikai on 2018-5-22.
 */
@RestController
@RequestMapping("/manager")
@Api(description = "用户基本信息")
public class ManagerBaseController {
    @Reference
    private ManagerBaseService managerBaseService;

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    @ApiOperation(value = "注册")
    public DataResult<Boolean> register(@RequestBody ManagerLoginModel model){
        return DataResult.ok(managerBaseService.register(model));
    }
}
