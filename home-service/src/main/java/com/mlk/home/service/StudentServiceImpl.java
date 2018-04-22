package com.mlk.home.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mlk.home.entity.Student;
import com.mlk.home.mapper.StudentMapper;
import com.mlk.home.page.PageInfo;
import com.mlk.home.search.StudentSearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by malikai on 2018-4-17.
 */
@Service(protocol = { "dubbo" }, timeout = 60000)
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student queryById(int id) {
        return studentMapper.queryById(id);
    }

    @Override
    public PageInfo<Student> queryAll(StudentSearchModel model) {
        PageHelper.startPage(model.getPage(),model.getRows(),true);
        Page<Student> list = studentMapper.queryAll(model);
        PageInfo pageInfo = new PageInfo<>(model.getPage(),model.getRows(),list.getTotal(),list.getResult());
        return pageInfo;
    }

    @Override
    public List<String> queryAllName() {
        return studentMapper.queryAllName();
    }

    @Override
    public List<String> queryAllAge() {
        return studentMapper.queryAllAge();
    }
}
