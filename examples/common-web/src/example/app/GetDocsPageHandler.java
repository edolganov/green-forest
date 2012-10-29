package example.app;


import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.app.GetDocsPage;
import example.common.app.GetDocsPage.Input;
import example.storage.Storage;

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
