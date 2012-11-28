package example.common.action;

import java.util.List;

import com.gf.Action;
import com.gf.util.Util;

import example.common.model.Doc;

public class CreateDocs extends Action<List<String>, List<Doc>>{

	public CreateDocs() {
		super();
	}

	public CreateDocs(List<String> names) {
		super(names);
	}
	
	public CreateDocs(String name) {
		super(Util.list(name));
	}
	
	

}
