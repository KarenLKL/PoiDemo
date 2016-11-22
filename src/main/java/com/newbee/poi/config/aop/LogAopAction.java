package com.newbee.poi.config.aop;

import com.newbee.poi.dao.LogMapper;
import com.newbee.poi.entity.Log;
import com.newbee.poi.utils.Common;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * AOP注解方法实现日志管理，利用spring AOP 切面技术记录日志 定义切面类
 * <p>
 * Created by Major on 2016/11/17.
 */
@EnableAspectJAutoProxy
@Component
public class LogAopAction {

    @Autowired
    private LogMapper logMapper;

    @Around("execution(* com.newbee.poi.dao.*.* (..))")
    public Object logAll(ProceedingJoinPoint point) {
        Object result = null;
        //执行方法名
        String methodName = point.getSignature().getName();
        String className = point.getSignature().getClass().getSimpleName();
        String user = null;
        Long start = 0L;
        Long end = 0L;
        String ip = null;

        try {
            result = point.proceed();
            //计算执行方法消耗的时间
            start = System.currentTimeMillis();
            end = System.currentTimeMillis();
            //IP
            ip = InetAddress.getLocalHost().getHostAddress();
            //登录名
            user = Common.getAuthenticatedUsername();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        String name = null;
        // 操作范围
        if (className.contains("Resources")) {
            name = "资源管理";
        } else if (className.contains("Roles")) {
            name = "角色管理";
        } else if (className.contains("User")) {
            name = "用户管理";
        }
        // 操作类型
        String opertype = "";
        if (methodName.contains("saveUserRole")) {
            opertype = "update用户的角色";
        } else if (methodName.contains("saveRoleRescours")) {
            opertype = "update角色的权限";
        } else if (methodName.contains("add") || methodName.contains("save")) {
            opertype = "save操作";
        } else if (methodName.contains("update") || methodName.contains("modify")) {
            opertype = "update操作";
        } else if (methodName.contains("delete")) {
            opertype = "delete操作";
        }
        if (!Common.isEmpty(opertype) && !className.contains("UserLoginList")) {
            Long time = end - start;
            Log log = new Log();
            log.setUserName(user);
            log.setModule(name);
            log.setAction(opertype);
            log.setActionTime(time.toString());
            log.setUserIP(ip);
            logMapper.save(log);
        }
        return result;

    }

}
