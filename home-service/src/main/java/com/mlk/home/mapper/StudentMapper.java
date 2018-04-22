package com.mlk.home.mapper;

import com.github.pagehelper.Page;
import com.mlk.home.entity.Student;
import com.mlk.home.search.StudentSearchModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;


public interface StudentMapper extends BaseMapper<Student>{

    Student queryById(@Param("id") int id);

    Page<Student> queryAll(StudentSearchModel model);

    List<String> queryAllName();

    List<String> queryAllAge();
}
