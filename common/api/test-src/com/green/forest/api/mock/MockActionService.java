package com.green.forest.api.mock;

import java.io.Serializable;

import com.green.forest.api.*;

public class MockActionService implements ActionService {

	@Override
	public <I extends Serializable, O extends Serializable> I invoke(
			Action<I, O> action) {
		// TODO Auto-generated method stub
		return null;
	}

}
