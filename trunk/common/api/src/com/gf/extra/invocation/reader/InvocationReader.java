package com.gf.extra.invocation.reader;

import java.util.List;

import com.gf.Handler;
import com.gf.InvocationObject;

public interface InvocationReader {

	List<InvocationObject> getLocalPrevObjects();
	
	List<InvocationObject> getLocalNextObjects();
	
	Handler<?> getHandler();
	
	//TODO
	//List<Object> getTotalPrevObjects();

}
