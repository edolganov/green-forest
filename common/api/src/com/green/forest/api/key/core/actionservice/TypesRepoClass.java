package com.green.forest.api.key.core.actionservice;

import com.green.forest.api.config.ConfigKey;

public class TypesRepoClass extends ConfigKey<Class<?>>{

	@Override
	public boolean hasDefaultValue() {
		return true;
	}
	
	@Override
	public Class<?> getDefaultValue() throws Exception  {
		return Class.forName("com.green.forest.core.repo.TypesRepoImpl");
	}

}
