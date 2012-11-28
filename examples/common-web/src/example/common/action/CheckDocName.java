package example.common.action;

import com.gf.Action;

import example.common.model.Doc;

public class CheckDocName extends Action<Doc, Void>{
	
	public CheckDocName(String name) {
		super(new Doc(-1, name));
	}

	public CheckDocName(long id, String name) {
		super(new Doc(id, name));
	}
	
	public CheckDocName(Doc doc) {
		super(doc);
	}
	
	

}
