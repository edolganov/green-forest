package com.green.forest.api.exception.deploy;

import com.green.forest.api.exception.InvocationException;

public abstract class DeployException extends InvocationException {

	public DeployException() {
		super();
	}

	public DeployException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeployException(String message) {
		super(message);
	}

	public DeployException(Throwable cause) {
		super(cause);
	}

	

}
