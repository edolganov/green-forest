package servlet_jdbc.common.app;

import servlet_jdbc.common.model.Doc;

import com.gf.Action;

public class RenameDoc extends Action<Doc, Doc>{
	
	public RenameDoc(int id, String newName){
		this(new Doc(id, newName));
	}

	public RenameDoc(Doc input) {
		super(input);
	}
	
	

}
