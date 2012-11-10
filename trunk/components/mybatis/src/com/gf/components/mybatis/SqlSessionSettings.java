package com.gf.components.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.ibatis.session.ExecutorType;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlSessionSettings {
	

	ExecutorType execType();
	
}
