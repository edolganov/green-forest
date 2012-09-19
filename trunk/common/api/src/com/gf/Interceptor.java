package com.gf;

import com.gf.service.InterceptorChain;

public abstract class Interceptor<T extends Action<?,?>> extends MappingObject {
	
	public abstract void invoke(T action, InterceptorChain chain) throws Exception;

}
