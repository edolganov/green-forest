package com.gf.key.core;

import com.gf.config.ConfigKey;

public class InvokeDepth extends ConfigKey<Integer>{

	@Override
	public boolean hasDefaultValue() {
		return true;
	}
	
	@Override
	public Integer getDefaultValue() throws Exception {
		return 100;
	}

}
