package servlet_jdbc.common.app;

import java.util.List;

import servlet_jdbc.common.model.Doc;

import com.gf.Action;


public class GetDocsPage extends Action<GetDocsPage.Input, List<Doc>>{
	
	public static class Input {
		
		public int page;
		public int limit;

		public Input(int page, int limit) {
			super();
			this.page = page;
			this.limit = limit;
		}

	}
	
	public GetDocsPage() {
		this(0);
	}

	public GetDocsPage(int page) {
		this(page, 50);
	}
	
	public GetDocsPage(int page, int limit) {
		super(new Input(page, limit));
	}
	
	

}
