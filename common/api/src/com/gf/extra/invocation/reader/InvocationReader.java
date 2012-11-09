package com.gf.extra.invocation.reader;

import java.util.List;

public interface InvocationReader {

	List<Object> getPrevHandlers();
	
	List<Object> getNextHandlers();

}
