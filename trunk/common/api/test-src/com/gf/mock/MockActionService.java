package com.gf.mock;

import java.io.Serializable;

import com.gf.Action;
import com.gf.ActionService;

public class MockActionService implements ActionService {

	@Override
	public <I extends Serializable, O extends Serializable> O invoke(
			Action<I, O> action) {
		// TODO Auto-generated method stub
		return null;
	}

}
