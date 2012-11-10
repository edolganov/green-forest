package com.gf.extra.invocation.reader;

import java.util.List;

public interface InvocationReader {

	List<Object> getLocalPrevHandlers();
	
	List<Object> getLocalNextHandlers();
	
	//TODO
	//List<Object> getTotalPrevHandlers();

}
