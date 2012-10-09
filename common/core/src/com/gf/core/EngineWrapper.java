package com.gf.core;

import com.gf.Action;
import com.gf.exception.BaseException;
import com.gf.service.ActionService;

public abstract class EngineWrapper implements ActionService {

	private Engine engine;

	public EngineWrapper(Engine engine) {
		super();
		this.engine = engine;
	}
	
	
	@Override
	public <I, O> O invoke(Action<I, O> action) throws BaseException {
		return engine.invoke(action);
	}

}
