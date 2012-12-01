package example.app;

import javax.sql.DataSource;


public class AppContext {
	
	public App app;
	public DataSource ds;
	
	public AppContext(App app, DataSource ds) {
		super();
		this.app = app;
		this.ds = ds;
	}

}
