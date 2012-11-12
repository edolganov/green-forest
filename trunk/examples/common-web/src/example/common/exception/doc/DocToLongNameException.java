package example.common.exception.doc;

import example.common.exception.ValidationException;

public class DocToLongNameException extends ValidationException {
	
	final public long id;
	final public String invalidName;
	final public int maxSize;

	public DocToLongNameException(long id, String invalidName, int maxSize) {
		super("To long name '"+invalidName+"' for doc with id "+id);
		this.id = id;
		this.invalidName = invalidName;
		this.maxSize = maxSize;
	}

	public long getId() {
		return id;
	}

	public String getInvalidName() {
		return invalidName;
	}

	public int getMaxSize() {
		return maxSize;
	}
	
	

}
