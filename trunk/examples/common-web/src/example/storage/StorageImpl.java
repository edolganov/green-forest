package example.storage;

import com.gf.common.ActionServiceWrapper;
import com.gf.service.ActionService;

public class StorageImpl extends ActionServiceWrapper implements Storage {

	public StorageImpl(ActionService actionService) {
		super(actionService);
	}

}
