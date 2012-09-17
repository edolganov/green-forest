package com.gf.core.deploy;

import java.util.List;

import com.gf.Action;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.exception.invoke.HandlerNotFoundException;
import com.gf.exception.invoke.NotOneHandlerException;

public interface ResourseService {
	
	Handler<?> getHandler(Action<?, ?> action) throws HandlerNotFoundException, NotOneHandlerException;

	List<Filter> getFilters();

	List<Interceptor<?>> getInterceptors(Action<?, ?> action);

}
