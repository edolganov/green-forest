package com.gf.core.repo;

import com.gf.core.repo.TypesRepo;
import com.gf.core.repo.TypesRepoImpl;


public class TypesRepoImplTest extends AbstractTypesRepoTest {

	@Override
	protected TypesRepo getRepo() {
		return new TypesRepoImpl();
	}
	
	
	

}


