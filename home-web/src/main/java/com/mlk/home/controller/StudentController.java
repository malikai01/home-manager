package com.mlk.home.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlk.home.entity.Student;
import com.mlk.home.page.PageInfo;
import com.mlk.home.search.StudentSearchModel;
import com.mlk.home.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by malikai on 2018-4-17.
 */
@Controller
@RequestMapping("mlk/studentTest")
@Api(description = "学生信息测试")
public class StudentController {
    @Reference
    private StudentService studentService;

    @RequestMapping(value = "/queryById",method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取学生信息")
    public String queryById(@RequestParam Integer id){
        Student stu = studentService.queryById(id);
        System.out.println("--------------"+stu.getName());
        return "student";
    }
    @RequestMapping(value = "/studentList",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Student> queryAll(StudentSearchModel model){
        PageInfo<Student> pageInfo=studentService.queryAll(model);
        return pageInfo;
    }
    @RequestMapping("/showChart")
    @ResponseBody
    public Object showChart(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> namelist=new ArrayList<String>();
        List<String> agelist=new ArrayList<String>();
        namelist=studentService.queryAllName();
        agelist=studentService.queryAllAge();
        map.put("namelist",namelist);
        map.put("agelist",agelist);
        return map;
    }

    @RequestMapping(value = "/xxx",method = RequestMethod.GET)
    @ApiOperation(value = "xxx")
    public String xxx(){
        return "myTest";
    }
}
