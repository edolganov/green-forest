package example.common.model;


public class Doc {
	
	public int id;
	public String name;
	
	
	public Doc() {
		super();
	}


	public Doc(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
