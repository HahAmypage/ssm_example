package com.davina.utils;

import com.davina.domain.SysLog;
import com.davina.service.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.sql.Timestamp;

@Component
@Aspect
public class LogAspect {

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest servletRequest;

    @Around("execution(* com.davina.controller.*.*(..))")
    public Object insertLog(ProceedingJoinPoint proceedingJoinPoint){

        // 获取用户名
        String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        // 获取当前执行的方法信息，如：com.itheima.controller.UserController.findByPage(..)
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String result = className+"."+methodName;

        // 初始化日志对象
        SysLog sysLog = new SysLog();
        sysLog.setIp(servletRequest.getRemoteAddr());
        sysLog.setMethod(result);
        sysLog.setUsername(username);
        sysLog.setVisitTime(new Timestamp(new Date().getTime()));

        try {
            Object proceed = proceedingJoinPoint.proceed();
            sysLogService.save(sysLog);
            return proceed;
        }catch (Throwable e){
            e.printStackTrace();
            return null;
        }
    }
}
