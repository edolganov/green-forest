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


import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.GetDocsPage;
import example.common.action.GetDocsPage.Input;
import example.storage.Storage;

@Mapping(GetDocsPage.class)
public class GetDocsPageHandler extends Handler<GetDocsPage>{
	
	@Inject
	Storage storage;

	@Override
	public void invoke(GetDocsPage action) throws Exception {
		
		Input input = action.input();
		int updatedPageIndex = input.pageIndex;
		int updatedLimit = input.limit;
		
		if(updatedPageIndex < 0){
			updatedPageIndex = 0;
		}
		
		if(updatedLimit < 0){
			updatedLimit = 0;
		} else if(updatedLimit > 50){
			updatedLimit = 50;
		}
		
		input.limit = updatedLimit;
		input.pageIndex = updatedPageIndex;
		
		storage.invoke(action);
		
	}

}
