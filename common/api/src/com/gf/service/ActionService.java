package com.gf.service;

import com.gf.Action;
import com.gf.exception.BaseException;


public interface ActionService {
	
	<I, O> O invoke(Action<I,O> action) throws BaseException;

}
