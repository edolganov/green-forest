package example.app;

import com.gf.common.ActionServiceWrapper;
import com.gf.service.ActionService;

public class App extends ActionServiceWrapper implements IApp {

	public App(ActionService actionService) {
		super(actionService);
	}
	

}
