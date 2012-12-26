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
package example.app.handler;

import java.util.List;

import com.gf.Action;
import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.service.InterceptorChain;
import com.gf.util.Util;

import example.common.action.validation.HasDocNames;
import example.common.exception.doc.DocEmptyNameException;
import example.common.exception.doc.DocToLongNameException;

@Mapping(HasDocNames.class)
public class CheckDocNameInterceptor extends Interceptor<Action<?,?>>{

	public static final int NAME_MAX_SIZE = 20;

	@Override
	public void invoke(Action<?, ?> action, InterceptorChain chain) throws Exception {
		
		HasDocNames hasDocNames = ((HasDocNames)action);
		List<String> names = hasDocNames.getNames();
		
		if(Util.isEmpty(names)){
			throw new DocEmptyNameException();
		}
		
		for (String name : names) {
			checkName(name);
		}
		
		chain.doNext();
		
	}

	private void checkName(String name) {
		if(Util.isEmpty(name)){
			throw new DocEmptyNameException();
		}

		if(name.length() > NAME_MAX_SIZE){
			throw new DocToLongNameException(name, NAME_MAX_SIZE);
		}
	}

}
