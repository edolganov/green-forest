package com.gf.service;

import java.util.Collection;

public interface ContextService {
	
	void addToContext(Object ob);
	
	void setContextObjects(Collection<Object> objects);

}
