package com.gf.core.action.reader;

import java.util.ArrayList;
import java.util.List;

import com.gf.core.action.InvocationContext;
import com.gf.extra.invocation.reader.InvocationReader;

public class InvocationReaderImpl implements InvocationReader {
	
	InvocationContext c;
	Object curHandler;

	public InvocationReaderImpl(InvocationContext context, Object currentHandler) {
		super();
		this.c = context;
		this.curHandler = currentHandler;
	}

	@Override
	public List<Object> getLocalPrevHandlers() {
		
		ArrayList<Object> out = new ArrayList<Object>();
		
		for(Object filter : c.filters){
			if(filter != curHandler){
				out.add(filter);
			} else {
				return out;
			}
		}
		
		for(Object interceptor : c.interceptors){
			if(interceptor != curHandler){
				out.add(interceptor);
			} else {
				return out;
			}
		}
		
		return out;
	}

	@Override
	public List<Object> getLocalNextHandlers() {
		
		ArrayList<Object> out = new ArrayList<Object>();
		boolean isAfter = false;
		
		for(Object filter : c.filters){
			if(filter == curHandler){
				isAfter = true;
			}else if(isAfter){
				out.add(filter);
			}
		}
		
		if(isAfter){
			out.addAll(c.interceptors);
		} else {
			for(Object interceptor : c.interceptors){
				if(interceptor == curHandler){
					isAfter = true;
				}else if(isAfter){
					out.add(interceptor);
				}
			}
		}
		
		if(isAfter){
			out.add(c.handler);
		}
		
		return out;
	}

}
