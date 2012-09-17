package com.gf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {
	
	public static final int DEFAULT_VAL = Integer.MAX_VALUE - 1;
	
    int value() default DEFAULT_VAL;

}
