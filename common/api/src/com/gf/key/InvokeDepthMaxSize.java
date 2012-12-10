package com.gf.key;

import com.gf.config.ConfigKey;

public class InvokeDepthMaxSize extends ConfigKey<Integer>{

	@Override
	public boolean hasDefaultValue() {
		return true;
	}
	
	@Override
	public Integer getDefaultValue() throws Exception {
		return 100;
	}

}
