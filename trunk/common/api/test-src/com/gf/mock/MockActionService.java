package com.gf.mock;

import com.gf.Action;
import com.gf.service.ActionService;

public class MockActionService implements ActionService {

	@Override
	public <I, O> O invoke(Action<I, O> action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I, O> O invokeUnwrap(Action<I, O> action) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
