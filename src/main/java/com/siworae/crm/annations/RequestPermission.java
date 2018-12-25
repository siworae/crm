package com.siworae.crm.annations;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @program: crm
 * @ClassName: RequestPermission
 * @Date: 2018/12/25 18:38
 * @Author: siworae
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface RequestPermission {
    String aclValue() default "";
}
