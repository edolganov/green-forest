package com.gf.core.action.reader;

import java.util.ArrayList;
import java.util.List;

import com.gf.Handler;
import com.gf.InvocationObject;
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
	public List<InvocationObject> getLocalPrevObjects() {
		
		ArrayList<InvocationObject> out = new ArrayList<InvocationObject>();
		
		for(InvocationObject filter : c.filters){
			if(filter != curHandler){
				out.add(filter);
			} else {
				return out;
			}
		}
		
		for(InvocationObject interceptor : c.interceptors){
			if(interceptor != curHandler){
				out.add(interceptor);
			} else {
				return out;
			}
		}
		
		return out;
	}

	@Override
	public List<InvocationObject> getLocalNextObjects() {
		
		ArrayList<InvocationObject> out = new ArrayList<InvocationObject>();
		boolean isAfter = false;
		
		for(InvocationObject filter : c.filters){
			if(filter == curHandler){
				isAfter = true;
			}else if(isAfter){
				out.add(filter);
			}
		}
		
		if(isAfter){
			out.addAll(c.interceptors);
		} else {
			for(InvocationObject interceptor : c.interceptors){
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

	@Override
	public Handler<?> getHandler() {
		return c.handler;
	}

}
