package com.gf.core;

import java.io.Serializable;

import com.gf.Action;
import com.gf.ActionService;
import com.gf.ConfigService;
import com.gf.DeployService;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.config.ConfigKey;
import com.gf.core.action.ActionServiceImpl;
import com.gf.core.config.ConfigServiceImpl;
import com.gf.core.deploy.DeployServiceImpl;
import com.gf.core.deploy.ResourseService;
import com.gf.exception.BaseException;
import com.gf.exception.deploy.NoMappingAnnotationException;
import com.gf.exception.deploy.NotOneHandlerException;

public class Engine implements ActionService, DeployService, ConfigService {
	
	private ConfigService config;
	private DeployService deploy;
	private ResourseService resourse;
	private ActionService actions;
	
	
	public Engine() {
		
		config = new ConfigServiceImpl();
		deploy = new DeployServiceImpl(config);
		resourse = (ResourseService)deploy;
		actions = new ActionServiceImpl(config, resourse);
		
	}
	

	@Override
	public <I extends Serializable, O extends Serializable> O invoke(
			Action<I, O> action) throws BaseException {
		return (O)actions.invoke(action);
	}


	@Override
	public void putHandler(Class<? extends Handler<?>> clazz)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.putHandler(clazz);
	}


	@Override
	public void putInterceptor(Class<? extends Interceptor<?>> clazz)
			throws NoMappingAnnotationException {
		deploy.putInterceptor(clazz);
	}


	@Override
	public void putFilter(Class<? extends Filter> clazz) {
		deploy.putFilter(clazz);
	}


	@Override
	public <T> T getValue(ConfigKey<T> key) {
		return (T) config.getValue(key);
	}


	@Override
	public boolean isTrue(ConfigKey<Boolean> key) {
		return config.isTrue(key);
	}


	@Override
	public <T> void addValue(Class<? extends ConfigKey<T>> keyType, T value) {
		config.addValue(keyType, value);
	}
	

}
