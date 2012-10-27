package servlet_jdbc.common.exception.doc;

import servlet_jdbc.common.exception.ValidationException;

public class DocToLongNameException extends ValidationException {
	
	final public int id;
	final public String invalidName;

	public DocToLongNameException(int id, String invalidName) {
		super("To long name '"+invalidName+"' for doc with id "+id);
		this.id = id;
		this.invalidName = invalidName;
	}

	public int getId() {
		return id;
	}

	public String getInvalidName() {
		return invalidName;
	}

}
