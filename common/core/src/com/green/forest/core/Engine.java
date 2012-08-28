package com.green.forest.core;

import java.io.Serializable;

import com.green.forest.api.Action;
import com.green.forest.api.ActionService;
import com.green.forest.api.ConfigService;
import com.green.forest.api.DeployService;
import com.green.forest.api.Filter;
import com.green.forest.api.Handler;
import com.green.forest.api.Interceptor;
import com.green.forest.api.config.ConfigKey;
import com.green.forest.api.exception.BaseException;
import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.api.exception.deploy.NotOneHandlerException;
import com.green.forest.core.action.ActionServiceImpl;
import com.green.forest.core.config.ConfigServiceImpl;
import com.green.forest.core.deploy.DeployServiceImpl;
import com.green.forest.core.deploy.ResourseService;

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
