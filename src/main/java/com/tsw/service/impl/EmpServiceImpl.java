package com.tsw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tsw.mapper.EmpMapper;
import com.tsw.pojo.Emp;
import com.tsw.pojo.PageBean;
import com.tsw.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageBean page(Integer start, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        // PageHelper插件
        PageHelper.startPage(start, pageSize);
        List<Emp> empList = empMapper.getEmpList(name, gender, begin, end);
        Page<Emp> empPage = (Page<Emp>) empList;
        PageBean pageBean = new PageBean(empPage.getResult(), empPage.getTotal());
        return pageBean;
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        empMapper.deleteByIds(ids);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }
}
