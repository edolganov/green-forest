package com.gf.service;

import java.io.Serializable;

import com.gf.Action;

public interface InvocationService {
	
	<I extends Serializable,O extends Serializable> O subInvoke(Action<I, O> action) throws Exception;

}
