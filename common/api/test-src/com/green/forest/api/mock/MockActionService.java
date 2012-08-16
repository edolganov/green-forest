package com.green.forest.api.mock;

import java.io.Serializable;

import com.green.forest.api.Action;
import com.green.forest.api.ActionService;

public class MockActionService implements ActionService {

	@Override
	public <I extends Serializable, O extends Serializable> O invoke(
			Action<I, O> action) {
		// TODO Auto-generated method stub
		return null;
	}

}
