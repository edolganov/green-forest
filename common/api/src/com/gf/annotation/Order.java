package com.gf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {
	
	public static final int DEFAULT_ORDER = Integer.MAX_VALUE - 1;
	public static final int SYSTEM_ORDER = -100;
	
    int value() default DEFAULT_ORDER;

}
