package com.tsw.controller;

import com.tsw.pojo.Emp;
import com.tsw.pojo.Result;
import com.tsw.service.EmpService;
import com.tsw.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class LoginController {
    @Autowired
    private EmpService empService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        try {
            log.info("登录，参数：{}", emp);
            Emp e = empService.login(emp);
            if (e == null) {
                return Result.error("用户名或密码错误");
            }
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            claims.put("name", e.getName());
            String jwt = jwtUtils.generateToken(claims);
            return Result.success(jwt);
        } catch (Exception e) {
            log.info("登录失败" + e);
            return Result.error("登录失败");
        }
    }

}
