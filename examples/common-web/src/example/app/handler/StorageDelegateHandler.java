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

import com.gf.Action;
import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.CreateDocs;
import example.common.action.GetDocsCount;
import example.common.action.RenameDoc;
import example.storage.Storage;


@Mapping({
	GetDocsCount.class,
	CreateDocs.class,
	RenameDoc.class
})
public class StorageDelegateHandler extends Handler<Action<?,?>>{
	
	@Inject
	Storage storage;

	@Override
	public void invoke(Action<?, ?> action) throws Exception {
		
		storage.invoke(action);
		
	}

}
