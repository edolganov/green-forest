package com.gf;

import java.io.Serializable;

import com.gf.exception.BaseException;


public interface ActionService {
	
	<I extends Serializable, O extends Serializable> 
		O invoke(Action<I,O> action) throws BaseException;

}
