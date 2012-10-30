package com.gf.util;

public class ExceptionUtil {

	public static Exception getExceptionOrThrowError(Throwable t) {
		if (t instanceof Exception) {
			return (Exception) t;
		}
		if (t instanceof Error) {
			throw (Error) t;
		} else {
			throw new IllegalStateException("Unknow type of Throwable", t);
		}
	}

	public static RuntimeException getRuntimeExceptionOrThrowError(Throwable t) {
		Exception e = getExceptionOrThrowError(t);
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException("Wrap not-runtime exception", e);
		}
	}

}