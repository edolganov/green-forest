package example.storage;

import com.gf.common.ActionServiceWrapper;
import com.gf.service.ActionService;

public class Storage extends ActionServiceWrapper implements IStorage {

	public Storage(ActionService actionService) {
		super(actionService);
	}

}
