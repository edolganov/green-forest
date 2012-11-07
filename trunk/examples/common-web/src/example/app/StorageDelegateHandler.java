package example.app;

import com.gf.Action;
import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.app.CreateOrUpdateDataBase;
import example.storage.Storage;

@Mapping(CreateOrUpdateDataBase.class)
public class StorageDelegateHandler extends Handler<Action<?,?>>{
	
	@Inject
	Storage storage;

	@Override
	public void invoke(Action<?, ?> action) throws Exception {
		storage.invoke(action);
	}

}
