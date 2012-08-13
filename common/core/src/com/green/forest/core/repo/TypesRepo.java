package com.green.forest.core.repo;

import com.green.forest.api.exception.deploy.DeployException;

public interface TypesRepo {
	
	public void put(Class<?> clazz) throws DeployException;
	
	

}
