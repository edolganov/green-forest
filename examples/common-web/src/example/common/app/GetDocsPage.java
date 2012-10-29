package example.common.app;


import com.gf.Action;

import example.common.model.Doc;
import example.common.model.Page;


public class GetDocsPage extends Action<GetDocsPage.Input, Page<Doc>>{
	
	public static class Input {
		
		public int pageIndex;
		public int limit;

		public Input(int pageIndex, int limit) {
			super();
			this.pageIndex = pageIndex;
			this.limit = limit;
		}

	}
	
	public GetDocsPage(int pageIndex, int limit) {
		super(new Input(pageIndex, limit));
	}
	
	

}
