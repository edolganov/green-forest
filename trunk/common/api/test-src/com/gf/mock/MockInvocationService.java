package com.gf.mock;

import com.gf.Action;
import com.gf.service.InvocationService;

@SuppressWarnings("unchecked")
public class MockInvocationService implements InvocationService {
	
	Object singleReturnVal;

	@Override
	public <I, O> O subInvoke(Action<I, O> action) throws Exception {
		
		if(singleReturnVal != null){
			action.setOutput((O)singleReturnVal);
			return (O)singleReturnVal;
		}
		
		return null;
	}

	public void setSingleReturnValue(Object singleReturnVal) {
		this.singleReturnVal = singleReturnVal;
	}

}
