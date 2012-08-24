package com.green.forest.core.deploy;

import java.util.List;

import com.green.forest.api.Action;
import com.green.forest.api.Filter;
import com.green.forest.api.exception.invoke.HandlerNotFoundException;
import com.green.forest.api.exception.invoke.NotOneHandlerException;

public interface ResourseService {

	Class<?> getHandlerType(Action<?, ?> action) 
			throws HandlerNotFoundException, NotOneHandlerException;

	List<Filter> getFilters(Action<?, ?> action);

}
