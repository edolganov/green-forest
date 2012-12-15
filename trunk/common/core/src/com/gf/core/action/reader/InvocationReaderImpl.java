/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
