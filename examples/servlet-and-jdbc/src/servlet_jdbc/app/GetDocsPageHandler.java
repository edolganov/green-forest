package servlet_jdbc.app;

import servlet_jdbc.common.app.GetDocsPage;
import servlet_jdbc.common.app.GetDocsPage.Input;
import servlet_jdbc.storage.Storage;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

@Mapping(GetDocsPage.class)
public class GetDocsPageHandler extends Handler<GetDocsPage>{
	
	@Inject
	Storage storage;

	@Override
	public void invoke(GetDocsPage action) throws Exception {
		
		Input input = action.input();
		int updatedPageIndex = input.pageIndex;
		int updatedLimit = input.limit;
		
		if(updatedPageIndex < 0){
			updatedPageIndex = 0;
		}
		
		if(updatedLimit < 0){
			updatedLimit = 0;
		} else if(updatedLimit > 50){
			updatedLimit = 50;
		}
		
		input.limit = updatedLimit;
		input.pageIndex = updatedPageIndex;
		
		storage.invoke(action);
		
	}

}
