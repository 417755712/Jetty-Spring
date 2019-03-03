package com.zz.rest.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Transactional(value = "ApplicationTxManager", isolation = Isolation.READ_COMMITTED, 
				propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public @interface ApplicationTransaction {
}
