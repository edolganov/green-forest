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
import com.gf.annotation.Mapping;
import com.gf.util.Util;

import example.common.action.CheckDocName;
import example.common.exception.doc.DocEmptyNameException;
import example.common.exception.doc.DocToLongNameException;
import example.common.model.Doc;

@Mapping(CheckDocName.class)
public class CheckDocNameHandler extends Handler<CheckDocName>{

	@Override
	public void invoke(CheckDocName action) throws Exception {
		
		Doc doc = action.input();
		long id = doc.id;
		String name = doc.name;
		
		if(Util.isEmpty(name)){
			throw new DocEmptyNameException(id);
		}
		
		int maxNameSize = 20;
		if(name.length() > maxNameSize){
			throw new DocToLongNameException(id, name, maxNameSize);
		}
		
	}

}
