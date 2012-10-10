package com.gf.core.engine.deploy.scan;

import com.gf.Action;
import com.gf.Handler;
import com.gf.annotation.Mapping;

@Mapping(Action.class)
public class ScanHandler extends Handler<Action<?,?>>{

	@Override
	public void invoke(Action<?, ?> action) throws Exception {
		
	}

}
