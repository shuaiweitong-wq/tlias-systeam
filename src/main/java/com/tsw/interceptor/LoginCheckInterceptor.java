package com.tsw.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tsw.pojo.Result;
import com.tsw.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override // 目标方法执行之前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwt = request.getHeader("token");
        if (jwt == null || jwt.equals("")) {
            Result result = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(result);
            response.getWriter().write(jsonString);
            return false;
        }
        try {
            JwtUtils.parseToken(jwt);
        } catch (Exception e) {
            Result result = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(result);
            response.getWriter().write(jsonString);
            return false;
        }
        log.info("jwt验证成功");
        return true;
    }
    @Override // 目标方法执行之后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle()方法被调用");
    }
    @Override // 页面渲染之后执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion()方法被调用");
    }
}
