package com.green.forest.core;

import java.io.Serializable;

import com.green.forest.api.Action;
import com.green.forest.api.ActionService;
import com.green.forest.api.DeployService;
import com.green.forest.api.Filter;
import com.green.forest.api.Handler;
import com.green.forest.api.Interceptor;
import com.green.forest.api.exception.BaseException;
import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.api.exception.deploy.NotOneHandlerException;
import com.green.forest.core.action.ActionServiceImpl;
import com.green.forest.core.config.ConfigService;
import com.green.forest.core.config.ConfigServiceImpl;
import com.green.forest.core.deploy.DeployServiceImpl;
import com.green.forest.core.deploy.ResourseService;

public class Engine implements ActionService, DeployService {
	
	private ConfigService config;
	private DeployService deployService;
	private ResourseService resourseService;
	private ActionService actionService;
	
	
	public Engine() {
		
		config = new ConfigServiceImpl();
		deployService = new DeployServiceImpl(config);
		resourseService = (ResourseService)deployService;
		actionService = new ActionServiceImpl(resourseService);
		
	}
	

	@Override
	public <I extends Serializable, O extends Serializable> O invoke(
			Action<I, O> action) throws BaseException {
		return (O)actionService.invoke(action);
	}


	@Override
	public void putHandler(Class<? extends Handler<?>> clazz)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deployService.putHandler(clazz);
	}


	@Override
	public void putInterceptor(Class<? extends Interceptor<?>> clazz)
			throws NoMappingAnnotationException {
		deployService.putInterceptor(clazz);
	}


	@Override
	public void putFilter(Class<? extends Filter> clazz) {
		deployService.putFilter(clazz);
	}


	
	@Override
	public void setHandlerBlocking(Class<? extends Handler<?>> clazz,
			boolean val) {
		throw new UnsupportedOperationException();
	}


	@Override
	public void setInterceptorBlocking(Class<? extends Interceptor<?>> clazz,
			boolean val) {
		throw new UnsupportedOperationException();
	}


	@Override
	public void setFilterBlocking(Class<? extends Filter> clazz, boolean val) {
		throw new UnsupportedOperationException();
	}


	@Override
	public void setActionBlocking(Class<? extends Action<?, ?>> clazz,
			boolean val) {
		throw new UnsupportedOperationException();
	}


	@Override
	public void removeHandler(Class<? extends Handler<?>> clazz) {
		throw new UnsupportedOperationException();
	}


	@Override
	public void removeInterceptor(Class<? extends Interceptor<?>> clazz) {
		throw new UnsupportedOperationException();
	}


	@Override
	public void removeFilter(Class<? extends Filter> clazz) {
		throw new UnsupportedOperationException();
	}
	

}
