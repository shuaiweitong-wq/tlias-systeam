package com.tsw.filter;

import com.alibaba.fastjson.JSONObject;
import com.tsw.pojo.Result;
import com.tsw.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("application/json;charset=UTF-8");

        // 获取请求路径
        String path = request.getRequestURI().toString();

        if (path.contains("login")) {
            log.info("正在登录...");
            filterChain.doFilter(request, response);
            return;
        }

        // 获取jwt
        String jwt = request.getHeader("token");

        if (!StringUtils.hasLength(jwt)) {
            log.info("jwt为空");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return;
        }
        try {
            JwtUtils.parseToken(jwt);
        } catch (Exception e) {
            log.info("解析jwt失败");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return;
        }
        log.info("jwt验证成功");
        filterChain.doFilter(request, response);
    }
}
