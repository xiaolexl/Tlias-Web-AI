package com.itxiaole.Interceptor;


import com.itxiaole.untils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(uri.contains("/login")){
            log.info("登录信息");
            return true;
        }
        String token = request.getHeader("token");
        if(token==null || token.isEmpty()){
            log.info("无令牌");
            response.setStatus(401);
            return false;
        }
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌失效");
            response.setStatus(401);
            return false;
        }
        log.info("令牌验证成功");
        return true;
    }
}
