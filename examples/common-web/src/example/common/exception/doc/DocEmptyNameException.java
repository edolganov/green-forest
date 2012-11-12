package example.common.exception.doc;

import example.common.exception.ValidationException;

public class DocEmptyNameException extends ValidationException {
	
	final public long id;

	public DocEmptyNameException(long id) {
		super("empty name of doc with id "+id);
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
	

}
