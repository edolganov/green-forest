package servlet_jdbc.common.app;

import java.util.List;

import servlet_jdbc.common.model.Doc;

import com.gf.Action;


public class GetDocsPage extends Action<Integer, List<Doc>>{

	public GetDocsPage() {
		super();
	}

	public GetDocsPage(Integer input) {
		super(input);
	}
	
	

}
