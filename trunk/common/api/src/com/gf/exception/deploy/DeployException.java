package com.gf.exception.deploy;

import com.gf.exception.BaseException;

public abstract class DeployException extends BaseException {

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
