package com.gf;

import java.io.Serializable;

public interface InvocationControll {
	
	<I extends Serializable,O extends Serializable> O subInvoke(Action<I, O> action) throws Exception;

}
