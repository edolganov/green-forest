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
package jdbc.storage.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.RenameDoc;
import example.common.model.Doc;

@Mapping(RenameDoc.class)
public class RenameDocHandler extends Handler<RenameDoc>{
	
	@Inject
	Connection c;

	@Override
	public void invoke(RenameDoc action) throws Exception {
		
		Doc input = action.input();
		long id = input.id;
		String newName = input.name;
		
		PreparedStatement ps = c.prepareStatement("UPDATE doc SET name=? WHERE id=?");
		ps.setLong(2, id);
		ps.setString(1, newName);
		ps.executeUpdate();
		
	}

}
