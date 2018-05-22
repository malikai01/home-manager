package com.mlk.home.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlk.home.common.utils.DataResult;
import com.mlk.home.common.utils.EmptyUtils;
import com.mlk.home.common.utils.Message;
import com.mlk.home.entity.ManagerFamilyGroup;
import com.mlk.home.entity.ManagerLogin;
import com.mlk.home.service.ManagerBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by malikai on 2018-5-22.
 */
@Controller
@RequestMapping("/manager")
@Api(description = "用户基本信息")
public class ManagerBaseController {
    @Reference
    private ManagerBaseService managerBaseService;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/register/nextStep",method = RequestMethod.GET)
    public String nextStep(){
        return "nextStep";
    }

    @RequestMapping(value = "/register/add" , method = RequestMethod.POST)
    @ApiOperation(value = "注册")//firstStep
    public Message add(@RequestBody ManagerLogin model, HttpServletRequest request){
        Message msg = new Message();
        Long loginId=managerBaseService.register(model);
        if(EmptyUtils.isNotEmpty(loginId)) {
            request.setAttribute("loginId", loginId);
            msg.setSuccess(true);
            msg.setMsg("注册成功！");
            msg.setObj(loginId);
        }else {
            msg.setSuccess(false);
            msg.setMsg("注册失败！");
        }
        return msg;
    }
    @RequestMapping(value = "/register/binding" , method = RequestMethod.POST)
    @ApiOperation(value = "绑定家庭成员")//secondStep
    public DataResult<Boolean> binding(@RequestBody List<ManagerFamilyGroup> list){
        return DataResult.ok(managerBaseService.binding(list));
    }
    @RequestMapping(value = "/register/cancelBinding" , method = RequestMethod.POST)
    @ApiOperation(value = "取消绑定家庭成员")
    public DataResult<Boolean> cancelBinding(Long id){
        return DataResult.ok(managerBaseService.cancelBinding(id));
    }
}
