package jdbc.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.app.RenameDoc;
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
		
		input.setName(newName);
		Doc updated = input;
		action.setOutput(updated);
	}

}
