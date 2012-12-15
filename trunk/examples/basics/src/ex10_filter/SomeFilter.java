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
package ex10_filter;

import java.util.Date;

import com.gf.Action;
import com.gf.Filter;
import com.gf.service.FilterChain;


public class SomeFilter extends Filter {

	
	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		Date begin = new Date();
		log.info("begin: "+begin);
		
		try {
			//next handler
			chain.doNext();
		}finally {
			long worktime = System.currentTimeMillis() - begin.getTime();
			log.info("worktime: "+worktime+"ms");
		}
		
	}

}
