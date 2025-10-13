package com.tsw.controller;

import com.tsw.pojo.Emp;
import com.tsw.pojo.PageBean;
import com.tsw.pojo.Result;
import com.tsw.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping("")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam (defaultValue = "5") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat (pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat (pattern = "yyyy-MM-dd") LocalDate end){
        PageBean pageBean = null;
        try {
            log.info("员工分页查询，参数：start={}，pageSize={}，name={}，gender={}，begin={}，end={end}", page, pageSize, name, gender, begin, end);
            pageBean = empService.page(page, pageSize, name, gender, begin, end);
        } catch (Exception e) {
            log.error("员工分页查询失败：{}", e.getMessage());
            return Result.error("员工分页查询失败");
        }
        return Result.success(pageBean);
    }
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        try {
            log.info("批量删除员工，参数：{}", ids);
            empService.deleteByIds(ids);
        } catch (Exception e) {
            log.error("批量删除员工失败：{}", e.getMessage());
            return Result.error("批量删除员工失败");
        }
        return Result.success();
    }
    @PostMapping
    public Result add(@RequestBody Emp emp){
        try {
            log.info("添加员工，参数：{}", emp);
            empService.add(emp);
        } catch (Exception e) {
            log.error("添加员工失败：{}", e.getMessage());
            return Result.error("添加员工失败");
        }
        return Result.success();
    }
}
