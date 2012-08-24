package com.green.forest.api;

import java.io.Serializable;

import com.green.forest.api.exception.BaseException;


public interface ActionService {
	
	<I extends Serializable, O extends Serializable> 
		O invoke(Action<I,O> action) throws BaseException;

}
