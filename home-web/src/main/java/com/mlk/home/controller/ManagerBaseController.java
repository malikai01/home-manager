package com.mlk.home.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlk.home.UserContext;
import com.mlk.home.annotation.NeedAuthority;
import com.mlk.home.common.utils.*;
import com.mlk.home.cookie.CookieUtils;
import com.mlk.home.entity.ManagerFamilyGroup;
import com.mlk.home.entity.ManagerLogin;
import com.mlk.home.service.ManagerBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by malikai on 2018-5-22.
 */
@Controller
@RequestMapping("/manager")
@Api(description = "用户基本信息")
public class ManagerBaseController {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ManagerBaseController.class);
    @Reference
    private ManagerBaseService managerBaseService;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "/manager/register";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "/manager/login";
    }

    @RequestMapping(value = "/register/nextStep/{loginId}",method = RequestMethod.GET)
    public String nextStep(@PathVariable Long loginId,HttpServletRequest req){
        List<ManagerFamilyGroup> listBinding = managerBaseService.queryByLoginId(loginId);
        req.setAttribute("listBinding", listBinding);
        req.setAttribute("loginId", loginId);
        return "/manager/nextStep";
    }

    @RequestMapping(value = "/register/add" , method = RequestMethod.POST)
    @ApiOperation(value = "注册")//firstStep
    @ResponseBody
    public Message add(@RequestBody ManagerLogin model){
        Message msg = new Message();
        ManagerLogin response = managerBaseService.queryByLoginName(model.getLoginName());
        if(response!=null){
            msg.setSuccess(false);
            msg.setMsg("用户名已存在！");
            return msg;
        }
        Integer loginId=managerBaseService.register(model);
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

    @RequestMapping(value = "/toLogin" , method = RequestMethod.POST)
    @ApiOperation(value = "登录")//firstStep
    @ResponseBody
    public Message login(@RequestBody ManagerLogin model,HttpServletResponse httpServletResponse){
        //Cookie[] cookies =  request.getCookies();
        Message msg = new Message();
        ManagerLogin response=managerBaseService.login(model);
        if(response!=null) {
            String token = TokenUtils.createJwtToken(response.getLoginName());
            Cookie cookie = new Cookie("JWT", token);
            cookie.setPath("/");
            // 过期时间设为10min
            cookie.setMaxAge(60*10);
            httpServletResponse.addCookie(cookie);
            httpServletResponse.setHeader("access_token",token);
            UserContext.getInstance().setUser(response);
            msg.setSuccess(true);
            msg.setMsg("登录成功！");
            msg.setObj(response);
        }else {
            msg.setSuccess(false);
            msg.setMsg("登录失败，请稍后重试！");
        }
        return msg;
    }
    @ResponseBody
    @NeedAuthority
    @RequestMapping(value = "/modifyUserInfo")
    public String modifyUserInfo(HttpServletRequest request) {
         //ManagerLogin login = managerBaseService.queryByLoginName(CookieUtils.getName(request));
         ManagerLogin login1 = UserContext.getInstance().getUser();
        //logger.info("===="+login.getLoginName());
        logger.info("===="+login1.getLoginName());
        return "";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String test(){
        return "/manager/index";
    }
}
