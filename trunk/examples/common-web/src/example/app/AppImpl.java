package example.app;

import com.gf.common.ActionServiceWrapper;
import com.gf.service.ActionService;

public class AppImpl extends ActionServiceWrapper implements App {

	public AppImpl(ActionService actionService) {
		super(actionService);
	}
	

}
