package servlet_jdbc.app;

import servlet_jdbc.common.app.GetDocsPage;
import servlet_jdbc.storage.Storage;

import com.gf.Action;
import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;


@Mapping({
	GetDocsPage.class
})
public class ToStorageHandler extends Handler<Action<?,?>>{
	
	@Inject
	Storage storage;

	@Override
	public void invoke(Action<?, ?> action) throws Exception {
		storage.invoke(action);
	}

}
