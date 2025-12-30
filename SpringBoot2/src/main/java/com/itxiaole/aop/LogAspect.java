package com.itxiaole.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itxiaole.mapper.OperateLogMapper;
import com.itxiaole.pojo.OperateLog;
import com.itxiaole.untils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest request;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 环绕通知
     */
    @Around("@annotation(com.itxiaole.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        
        // 1. 获取操作人ID (模拟从Header获取)
        String userIdStr = request.getHeader("userId");
        Integer operateEmpId = null;
        try {
            // 1. 获取请求头中的令牌 (通常 header key 是 "token")
            String jwt = request.getHeader("token");

            // 2. 只有令牌存在且不为空时才解析
            if(jwt != null && !jwt.isEmpty()){
                // 3. 解析令牌 (假设你有一个 JwtUtils 工具类)
                Claims claims = JwtUtils.parseJWT(jwt);

                // 4. 获取ID (注意：根据你生成Token时的写法，这里可能是 "id" 也可能是 "sub")
                operateEmpId = (Integer) claims.get("id");
            }
        } catch (Exception e) {
            // 解析失败（比如令牌过期），为了不影响业务，这里可以只打印日志，不抛出异常
            // operateEmpId 保持为 null
            System.out.println("AOP解析Token失败: " + e.getMessage());
        }

        // 2. 准备数据
        LocalDateTime operateTime = LocalDateTime.now();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();

        // 3. 执行目标方法
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            long costTime = end - begin;

            // 4. 处理返回值
            String returnValue = null;
            if (result != null) {
                try {
                    returnValue = objectMapper.writeValueAsString(result);
                } catch (Exception e) {
                    returnValue = "Result serialization failed";
                }
            }

            // 5. 封装并保存日志
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateEmpId(operateEmpId);
            operateLog.setOperateTime(operateTime);
            operateLog.setClassName(className);
            operateLog.setMethodName(methodName);
            operateLog.setMethodParams(methodParams);
            operateLog.setReturnValue(returnValue);
            operateLog.setCostTime(costTime);

            operateLogMapper.insert(operateLog);
        }

        return result;
    }
}