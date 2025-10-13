package com.tsw.service;

import com.tsw.pojo.Emp;
import com.tsw.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    PageBean page(Integer start, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void deleteByIds(List<Integer> ids);

    void add(Emp emp);
}
