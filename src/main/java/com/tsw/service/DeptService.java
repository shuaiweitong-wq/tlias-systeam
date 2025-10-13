package com.tsw.service;

import com.tsw.pojo.Dept;
import com.tsw.pojo.Result;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {

    public List<Dept> getDeptList();

    void deleteDeptById(Integer id);

    void addDept(Dept dept);

    void updateDept(Dept dept);

    Dept getDeptById(Integer id);
}
