package com.gf.core;

import java.io.Serializable;

import com.gf.Action;
import com.gf.ActionService;
import com.gf.ConfigService;
import com.gf.ContextService;
import com.gf.DeployService;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.config.ConfigKey;
import com.gf.core.action.ActionServiceImpl;
import com.gf.core.config.ConfigServiceImpl;
import com.gf.core.context.ContextServiceImpl;
import com.gf.core.context.StaticContext;
import com.gf.core.deploy.DeployServiceImpl;
import com.gf.core.deploy.ResourseService;
import com.gf.exception.BaseException;
import com.gf.exception.deploy.NoMappingAnnotationException;
import com.gf.exception.deploy.NotOneHandlerException;

public class Engine implements ActionService, DeployService, ConfigService, ContextService {
	
	private ConfigService config;
	private DeployService deploy;
	private ActionService actions;
	private ContextService context;
	
	
	public Engine() {
		
		config = new ConfigServiceImpl();
		deploy = new DeployServiceImpl(config);
		context = new ContextServiceImpl(config);
		actions = new ActionServiceImpl(config, 
				(ResourseService)deploy,
				(StaticContext)context);
		
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
	

	@Override
	public void addToContext(Object object) {
		context.addToContext(object);
	}
	

}
