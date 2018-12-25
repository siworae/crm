package com.siworae.crm.aop;

import com.siworae.crm.annations.RequestPermission;
import com.siworae.crm.utils.AssertUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @program: crm
 * @ClassName: PermissionProxy
 * @Date: 2018/12/25 18:39
 * @Author: siworae
 */
@Component
@Aspect
public class PermissionProxy {

    @Autowired
    private HttpSession session;

    @Pointcut("@annotation(com.siworae.crm.annations.RequestPermission)")
    public void cut(){}

    @Around("cut()")
    public Object cut(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        RequestPermission annotation = method.getAnnotation(RequestPermission.class);
        String aclValue = annotation.aclValue();
        List<String> permissions = (List<String>) session.getAttribute("permissions");

        AssertUtil.isTrue(null==permissions || !permissions.contains(aclValue), "没有权限");

        return pjp.proceed();
    }
}
