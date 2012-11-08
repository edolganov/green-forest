package com.gf.core;

import java.util.Collection;
import java.util.Properties;

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
	
	private String name;
	private ConfigService config;
	private DeployService deploy;
	private ActionService actions;
	private ContextService context;
	
	
	public Engine() {
		this(null);
	}
	
	public Engine(String name) {

		this.name = name;
		config = new ConfigServiceImpl();
		deploy = new DeployServiceImpl(config);
		context = new ContextServiceImpl();
		actions = new ActionServiceImpl(
				this,
				config, 
				(ResourseService)deploy,
				(StaticContext)context);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public <I, O> O invoke(Action<I, O> action) 
			throws InvocationException, ExceptionWrapper, RuntimeException {
		return (O)actions.invoke(action);
	}
	
	@Override
	public <I, O> O invokeUnwrap(Action<I, O> action) 
			throws InvocationException, Exception {
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
	public void scanForAnnotations(Package pckg)
			throws NoMappingAnnotationException, NotOneHandlerException{
		deploy.scanForAnnotations(pckg);
	}
	
	@Override
	public void scanForAnnotations(String packageName)
			throws NoMappingAnnotationException, NotOneHandlerException {
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
	public void setConfigValues(Properties props) {
		config.setConfigValues(props);
	}
	

	@Override
	public void addToContext(Object object) {
		context.addToContext(object);
	}
	
	@Override
	public void setContextObjects(Collection<Object> objects) {
		context.setContextObjects(objects);
	}

	@Override
	public void setHandlerTypes(Collection<Class<? extends Handler<?>>> handlerTypes)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.setHandlerTypes(handlerTypes);
	}

	@Override
	public void setInterceptorTypes(Collection<Class<? extends Interceptor<?>>> interceptorTypes)
			throws NoMappingAnnotationException{
		deploy.setInterceptorTypes(interceptorTypes);
	}

	@Override
	public void setFilterTypes(Collection<Class<? extends Filter>> filterTypes) {
		deploy.setFilterTypes(filterTypes);
	}

	@Override
	public void setScanForAnnotationsPackages(Collection<String> packageNames)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.setScanForAnnotationsPackages(packageNames);
	}

	

}
