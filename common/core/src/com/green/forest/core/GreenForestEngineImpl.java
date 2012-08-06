package com.green.forest.core;

import java.io.Serializable;

import com.green.forest.api.Action;
import com.green.forest.api.GreenForestEngine;
import com.green.forest.api.Handler;

public class GreenForestEngineImpl implements GreenForestEngine {
	
	
	public void put(Class<? extends Handler<Action<?,?>>> handlerClass){
		
		
		
	}
	

	@Override
	public <I extends Serializable, O extends Serializable> I invoke(Action<I, O> action) {
		// TODO Auto-generated method stub
		return null;
	}

}
