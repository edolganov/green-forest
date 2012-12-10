package com.gf.core.engine.deploy;

import com.gf.core.deploy.TypesRepository;
import com.gf.core.deploy.TypesRepositoryImpl;


public class TypesRepoImplTest extends AbstractTypesRepoTest {

	@Override
	protected TypesRepository getRepo() {
		return new TypesRepositoryImpl();
	}
	
	
	

}


