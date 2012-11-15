package example.common.app;


import com.gf.Action;

import example.common.model.Doc;

public class RenameDoc extends Action<Doc, Void>{
	
	public RenameDoc(int id, String newName){
		this(new Doc(id, newName));
	}

	public RenameDoc(Doc input) {
		super(input);
	}
	
	

}
