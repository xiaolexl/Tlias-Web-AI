package com.itxiaole.filter;

import com.itxiaole.untils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        if(uri.contains("/login")){
            log.info("登录信息");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String token = request.getHeader("token");
        if(token==null || token.isEmpty()){
            log.info("无令牌");
            response.setStatus(401);
            return;
        }
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌失效");
            response.setStatus(401);
            return;
        }
        log.info("令牌验证成功");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
