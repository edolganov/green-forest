package com.gf.service;

import com.gf.Action;

public interface InvocationService {
	
	<I,O> O subInvoke(Action<I, O> action) throws Exception;

}
