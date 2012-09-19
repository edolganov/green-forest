package com.gf.service;

import java.io.Serializable;

import com.gf.Action;
import com.gf.exception.BaseException;


public interface ActionService {
	
	<I extends Serializable, O extends Serializable> 
		O invoke(Action<I,O> action) throws BaseException;

}
