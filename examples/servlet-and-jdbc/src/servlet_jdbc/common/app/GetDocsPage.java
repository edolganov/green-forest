package servlet_jdbc.common.app;

import servlet_jdbc.common.model.Doc;
import servlet_jdbc.common.model.Page;

import com.gf.Action;


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
