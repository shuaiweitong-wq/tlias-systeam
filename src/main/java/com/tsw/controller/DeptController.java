package com.tsw.controller;

import com.tsw.pojo.Dept;
import com.tsw.pojo.Result;
import com.tsw.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@RequestMapping
public class DeptController {
    @Autowired
    private DeptService deptService;
    @GetMapping("/depts")
    public Result getDeptList() {
        List<Dept> list = null;
        try {
            log.info("查询部门列表");
            list = deptService.getDeptList();
        } catch (Exception e) {
            log.error("查询部门列表失败：{}", e.getMessage());
            return Result.error("查询部门列表失败");
        }
        return Result.success(list);
    }
    @DeleteMapping("/depts/{id}")
    public Result deleteDeptById(@PathVariable Integer id) {
        try {
            log.info("删除部门入参：{}", id);
            deptService.deleteDeptById(id);
        } catch (Exception e) {
            log.error("删除部门失败：{}", e.getMessage());
            return Result.error("删除部门失败");
        }
        return Result.success();
    }
    @PostMapping("/depts")
    public Result addDept(@RequestBody Dept dept) {
        try {
            log.info("新增部门入参：{}", dept.toString());
            deptService.addDept(dept);
        } catch (Exception e) {
            log.error("新增部门失败：{}", e.getMessage());
            return Result.error("新增部门失败");
        }
        return Result.success();
    }
    @GetMapping("/depts/{id}")
    public Result getDeptById(@PathVariable Integer id) {
        Dept dept = null;
        try {
            log.info("查询部门入参：{}", id);
            dept = deptService.getDeptById(id);
        } catch (Exception e) {
            log.error("查询部门失败：{}", e.getMessage());
            return Result.error("查询部门失败");
        }
        return Result.success(dept);
    }
    @PutMapping("/depts")
    public Result updateDept(@RequestBody Dept dept) {
        try {
            log.info("更新部门入参：{}", dept.toString());
            deptService.updateDept(dept);
        } catch (Exception e) {
            log.error("更新部门失败：{}", e.getMessage());
            return Result.error("更新部门失败");
        }
        return Result.success();
    }
}
