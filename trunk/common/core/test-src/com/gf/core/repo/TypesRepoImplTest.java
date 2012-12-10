package com.gf.core.repo;

import com.gf.core.repo.TypesRepository;
import com.gf.core.repo.TypesRepositoryImpl;


public class TypesRepoImplTest extends AbstractTypesRepoTest {

	@Override
	protected TypesRepository getRepo() {
		return new TypesRepositoryImpl();
	}
	
	
	

}


