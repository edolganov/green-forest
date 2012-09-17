package com.gf.key.core.actionservice;

import com.gf.config.ConfigKey;

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
