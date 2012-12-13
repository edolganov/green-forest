package com.gf.core.engine.handler.model;

import com.gf.config.ConfigKey;

public class TestConfig extends ConfigKey<String>{

	@Override
	public boolean hasDefaultValue() {
		return false;
	}

}
