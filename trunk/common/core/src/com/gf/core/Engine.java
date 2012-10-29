package com.gf.core;

import com.gf.Action;
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
import com.gf.exception.ExceptionWrapper;
import com.gf.exception.deploy.NoMappingAnnotationException;
import com.gf.exception.deploy.NotOneHandlerException;
import com.gf.exception.invoke.InvocationException;
import com.gf.service.ActionService;
import com.gf.service.ConfigService;
import com.gf.service.ContextService;
import com.gf.service.DeployService;

public class Engine implements ActionService, DeployService, ConfigService, ContextService {
	
	private ConfigService config;
	private DeployService deploy;
	private ActionService actions;
	private ContextService context;
	
	
	public Engine() {
		
		config = new ConfigServiceImpl();
		deploy = new DeployServiceImpl(config);
		context = new ContextServiceImpl();
		actions = new ActionServiceImpl(config, 
				(ResourseService)deploy,
				(StaticContext)context);
		
	}
	

	@Override
	public <I, O> O invoke(Action<I, O> action) throws InvocationException, ExceptionWrapper, RuntimeException {
		return (O)actions.invoke(action);
	}
	
	@Override
	public <I, O> O invokeUnwrap(Action<I, O> action) throws InvocationException, Exception {
		return (O)actions.invokeUnwrap(action);
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
	public void scanForAnnotations(Package pckg) {
		deploy.scanForAnnotations(pckg);
	}
	
	@Override
	public void scanForAnnotations(String packageName) {
		deploy.scanForAnnotations(packageName);
	}


	@Override
	public <T> T getConfig(ConfigKey<T> key) {
		return (T) config.getConfig(key);
	}


	@Override
	public boolean isTrueConfig(ConfigKey<Boolean> key) {
		return config.isTrueConfig(key);
	}


	@Override
	public <T> void setConfig(Class<? extends ConfigKey<T>> keyType, T value) {
		config.setConfig(keyType, value);
	}
	

	@Override
	public void addToContext(Object object) {
		context.addToContext(object);
	}

	

}
