package com.green.forest.core.repo;

import java.util.Set;

import com.green.forest.api.exception.deploy.DeployException;

public interface TypesRepo {
	
	public void put(Class<?> handler) throws DeployException;
	
	public Set<Class<?>> getTypes(Class<?> target);

}
