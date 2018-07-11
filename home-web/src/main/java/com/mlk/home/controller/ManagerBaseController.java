package com.mlk.home.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlk.home.common.utils.DataResult;
import com.mlk.home.common.utils.EmptyUtils;
import com.mlk.home.common.utils.Message;
import com.mlk.home.entity.ManagerFamilyGroup;
import com.mlk.home.entity.ManagerLogin;
import com.mlk.home.search.ManagerLoginModel;
import com.mlk.home.service.ManagerBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return "register1";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/register/nextStep/{loginId}",method = RequestMethod.GET)
    public String nextStep(@PathVariable Long loginId,HttpServletRequest req){
        List<ManagerFamilyGroup> listBinding = managerBaseService.queryByLoginId(loginId);
        req.setAttribute("listBinding", listBinding);
        req.setAttribute("loginId", loginId);
        return "nextStep";
    }

    @RequestMapping(value = "/register/add" , method = RequestMethod.POST)
    @ApiOperation(value = "注册")//firstStep
    @ResponseBody
    public Message add(@RequestBody ManagerLogin model){
        Message msg = new Message();
        Long loginId=managerBaseService.register(model);
        if(loginId>0) {
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
    @ResponseBody
    public Message binding(@RequestBody ManagerFamilyGroup group){
        Message msg = new Message();
        Boolean result = managerBaseService.binding(group);
        if(result) {
            List<ManagerFamilyGroup> list = managerBaseService.queryByLoginId(group.getLoginId());
            msg.setSuccess(true);
            msg.setMsg("绑定成功！");
            msg.setObj(list);
        }else {
            msg.setSuccess(false);
            msg.setMsg("绑定失败！");
        }
        return msg;
    }
    @RequestMapping(value = "/register/cancelBinding/{id}" , method = RequestMethod.GET)
    @ApiOperation(value = "取消绑定家庭成员")
    @ResponseBody
    public Message cancelBinding(@PathVariable Long id){
        Message msg = new Message();
        Boolean result = managerBaseService.cancelBinding(id);
        if(result) {
            msg.setSuccess(true);
            msg.setMsg("删除成功！");
        }else {
            msg.setSuccess(false);
            msg.setMsg("删除失败！");
        }
        return msg;
    }

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    @ApiOperation(value = "登录")//firstStep
    @ResponseBody
    public Message login(@RequestBody ManagerLogin model){
        Message msg = new Message();
        ManagerLogin response=managerBaseService.login(model);
        if(response!=null) {
            msg.setSuccess(true);
            msg.setMsg("登录成功！");
            msg.setObj(response);
        }else {
            msg.setSuccess(false);
            msg.setMsg("登录失败，请稍后重试！");
        }
        return msg;
    }
}
