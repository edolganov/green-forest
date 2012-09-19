package com.gf.exception.invoke;

import java.lang.reflect.Field;

public class ObjectToInjectNotFoundException extends InjectException {

	
	public ObjectToInjectNotFoundException(Object target, Field field) {
		super("can't find object to inject to ["+field+"] for "+target);
	}
}
