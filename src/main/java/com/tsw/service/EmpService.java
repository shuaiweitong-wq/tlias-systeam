package com.tsw.service;

import com.tsw.pojo.PageBean;

import java.time.LocalDate;

/**
 * 员工管理
 */
public interface EmpService {
    PageBean page(Integer start, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);
}
