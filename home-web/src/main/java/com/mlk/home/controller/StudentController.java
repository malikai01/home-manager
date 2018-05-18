package com.mlk.home.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlk.home.common.utils.ExcelUtil;
import com.mlk.home.entity.Student;
import com.mlk.home.page.PageInfo;
import com.mlk.home.search.StudentSearchModel;
import com.mlk.home.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
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
        return "register";
    }

    @RequestMapping(value = "/studentExport",method = RequestMethod.GET)
    public void partExport(HttpServletResponse response, StudentSearchModel studentSearchModel){
        PageInfo<Student> page=studentService.queryAll(studentSearchModel);
        List<Student> list=page.getRows();
        System.out.println("-------------"+list.toString());
        //JSONArray jsonArr = JSONArray.fromObject(list.toArray());
        JSONArray jsonArr = new JSONArray();
        for (Student a:list
                ) {
            JSONObject obj=new JSONObject();
            obj.put("id",a.getId());
            obj.put("name",a.getName());
            obj.put("age",a.getAge());
            obj.put("height",a.getHeight());
            obj.put("weight",a.getWeight());
            jsonArr.add(obj);
        }
        //获取业务数据集
        Map<String,String> headMap = new HashMap<>();//获取属性-列头
        headMap.put("name","姓名");
        headMap.put("age","年龄");
        headMap.put("height","身高");
        headMap.put("weight","体重");
        String title = "学生信息统计表";
        ExcelUtil.downloadExcelFile(title,headMap,jsonArr,response);
    }
}
