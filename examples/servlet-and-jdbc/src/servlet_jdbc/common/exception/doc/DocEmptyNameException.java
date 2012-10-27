package servlet_jdbc.common.exception.doc;

import servlet_jdbc.common.exception.ValidationException;

public class DocEmptyNameException extends ValidationException {
	
	final public int id;

	public DocEmptyNameException(int id) {
		super("empty name of doc with id "+id);
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	

}
