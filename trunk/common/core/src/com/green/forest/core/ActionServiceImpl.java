package com.green.forest.core;

import java.io.Serializable;

import com.green.forest.api.Action;
import com.green.forest.api.ActionService;
import com.green.forest.api.Handler;
import com.green.forest.api.key.core.actionservice.TypesRepoClass;
import com.green.forest.core.config.ConfigService;
import com.green.forest.core.repo.TypesRepo;

public class ActionServiceImpl implements ActionService {
	
	private ConfigService config;
	private TypesRepo handlersTypeRepo;
	
	public ActionServiceImpl(ConfigService config) {
		
		this.config = config;
		
		Class<?> typeRepoClass = config.getValue(new TypesRepoClass());
		
		this.handlersTypeRepo = CoreUtil.createInstance(typeRepoClass);
		handlersTypeRepo.setOneHandlerOnly(true);
			
		
	}
	
	
	public void put(Class<? extends Handler<Action<?,?>>> handlerClass){
		handlersTypeRepo.put(handlerClass);
	}
	

	@Override
	public <I extends Serializable, O extends Serializable> I invoke(Action<I, O> action) {
		// TODO Auto-generated method stub
		return null;
	}

}
