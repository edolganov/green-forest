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
package example.common.action;

import java.util.List;

import com.gf.Action;
import com.gf.util.Util;

import example.common.action.validation.HasDocNames;
import example.common.model.Doc;

public class CreateDocs extends Action<List<String>, List<Doc>> implements HasDocNames {

	public CreateDocs() {
		super();
	}

	public CreateDocs(List<String> names) {
		super(names);
	}
	
	public CreateDocs(String name) {
		super(Util.list(name));
	}

	public List<String> getNames() {
		return input;
	}
	
	

}
