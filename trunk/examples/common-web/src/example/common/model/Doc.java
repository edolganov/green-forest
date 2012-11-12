package example.common.model;


public class Doc {
	
	public long id;
	public String name;
	
	
	public Doc() {
		super();
	}


	public Doc(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
