package com.mlk.home.service;


import com.mlk.home.entity.Student;
import com.mlk.home.page.PageInfo;
import com.mlk.home.search.StudentSearchModel;

import java.util.List;

/**
 * Created by malikai on 2018-4-17.
 */
public interface StudentService {
    Student queryById(int id);

    PageInfo<Student> queryAll(StudentSearchModel model);

    List<String> queryAllName();

    List<String> queryAllAge();
}
