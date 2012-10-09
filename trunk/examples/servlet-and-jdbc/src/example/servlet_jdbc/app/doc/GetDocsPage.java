package example.servlet_jdbc.app.doc;

import java.util.List;

import com.gf.Action;

import example.servlet_jdbc.model.Doc;

public class GetDocsPage extends Action<Integer, List<Doc>>{

	public GetDocsPage() {
		super();
	}

	public GetDocsPage(Integer input) {
		super(input);
	}
	
	

}
