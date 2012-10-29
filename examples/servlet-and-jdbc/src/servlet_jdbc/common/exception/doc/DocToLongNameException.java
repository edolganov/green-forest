package servlet_jdbc.common.exception.doc;

import servlet_jdbc.common.exception.ValidationException;

public class DocToLongNameException extends ValidationException {
	
	final public int id;
	final public String invalidName;
	final public int maxSize;

	public DocToLongNameException(int id, String invalidName, int maxSize) {
		super("To long name '"+invalidName+"' for doc with id "+id);
		this.id = id;
		this.invalidName = invalidName;
		this.maxSize = maxSize;
	}

	public int getId() {
		return id;
	}

	public String getInvalidName() {
		return invalidName;
	}

	public int getMaxSize() {
		return maxSize;
	}
	
	

}
