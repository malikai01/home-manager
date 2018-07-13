package com.mlk.home.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlk.home.UserContext;
import com.mlk.home.annotation.NeedAuthority;
import com.mlk.home.common.utils.Message;
import com.mlk.home.entity.ManagerLogin;
import com.mlk.home.entity.TakeNames;
import com.mlk.home.page.PageInfo;
import com.mlk.home.search.TakeNamesModel;
import com.mlk.home.service.TakeNamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by malikai on 2018-7-9.
 */
@Controller
@RequestMapping("v1/takeNames")
public class TakeNamesController {
    @Reference
    private TakeNamesService takeNamesService;
    @RequestMapping("/addHtml")
    public String addHtml(){
        return "takeNames/takeNames_add";
    }
    @RequestMapping("/queryHtml")
    public String queryHtml(){
        return "takeNames/takeNames_list";
    }
    @RequestMapping("/editHtml/{cId}")
    public String editHtml(@PathVariable Integer cId,HttpServletRequest request){
        TakeNames takeNames = takeNamesService.queryById(cId);
        request.setAttribute("takeNames",takeNames);
        return "takeNames/takeNames_edit";
    }
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    @NeedAuthority
    public Message editNames(@RequestBody TakeNames names){
        ManagerLogin login = UserContext.getInstance().getUser();
        names.setUpdateTime(new Date());
        names.setIsShow("1");
        names.setFamilyId(login.getId());
        Message msg = new Message();
        boolean result = takeNamesService.editNnames(names);
        msg.setSuccess(result);
        msg.setMsg("修改成功");
        return msg;
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    @NeedAuthority
    public Message addNames(@RequestBody TakeNames names){
        ManagerLogin login = UserContext.getInstance().getUser();
        names.setCreateTime(new Date());
        names.setUpdateTime(new Date());
        names.setIsShow("1");
        names.setFamilyId(login.getId());
        Message msg = new Message();
        boolean result = takeNamesService.addNnames(names);
        msg.setSuccess(result);
        msg.setMsg("修改成功");
        return msg;
    }
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @NeedAuthority
    public PageInfo<TakeNames> queryNames( TakeNamesModel model){
        ManagerLogin login = UserContext.getInstance().getUser();
        model.setFamilyId(login.getId());
        return takeNamesService.queryNames(model);
    }
}
