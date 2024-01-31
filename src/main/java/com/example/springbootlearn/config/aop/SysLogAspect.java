package com.example.springbootlearn.config.aop;

import com.example.springbootlearn.entity.sys.SysLogBean;
import com.example.springbootlearn.service.aop.SysLogService;
import com.example.springbootlearn.utils.toolkit.HttpContextUtils;
import com.example.springbootlearn.utils.toolkit.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Project springboot-learn
 * @Description 系统日志, 切面处理类
 * @Author xuzhenshan
 * @Date 2024/1/30 16:10:45
 * @Version 1.0
 */
@Aspect
@Component
public class SysLogAspect {

    private final SysLogService sysLogService;

    public SysLogAspect(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @Pointcut("@annotation(com.example.springbootlearn.config.aop.SysLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object method = point.proceed();
        // 执行时长
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveSysLog(point, time);
        return method;
    }

    /**
     * 保存日志
     *
     * @param joinPoint 执行方法
     * @param time      执行时长
     */
    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLogBean sysLog = new SysLogBean();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            // 方法描述
            sysLog.setOperation(syslog.value());
        }

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = Arrays.toString(args);
            sysLog.setParams(params);
        } catch (Exception ignored) {
        }

        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIpAddress(IPUtils.getIpAddr(request));
        sysLog.setTime(time);
        sysLog.setCreateDate(System.currentTimeMillis());
        //保存系统日志
        sysLogService.save(sysLog);
    }
}
