package com.gf.components.log.slf4j;

import org.slf4j.Logger;

import com.gf.log.Log;

public class LogImpl implements Log {
	
	private final Logger log;

	public LogImpl(Logger log) {
		super();
		this.log = log;
	}

	@Override
	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	@Override
	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	@Override
	public boolean isFatalEnabled() {
		return log.isErrorEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	@Override
	public boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	@Override
	public void trace(Object message) {
		log.trace(String.valueOf(message));
	}

	@Override
	public void trace(Object message, Throwable t) {
		log.trace(String.valueOf(message), t);
	}

	@Override
	public void debug(Object message) {
		log.debug(String.valueOf(message));
	}

	@Override
	public void debug(Object message, Throwable t) {
		log.debug(String.valueOf(message), t);
	}

	@Override
	public void info(Object message) {
		log.info(String.valueOf(message));
	}

	@Override
	public void info(Object message, Throwable t) {
		log.info(String.valueOf(message), t);
	}

	@Override
	public void warn(Object message) {
		log.warn(String.valueOf(message));
	}

	@Override
	public void warn(Object message, Throwable t) {
		log.warn(String.valueOf(message), t);
	}

	@Override
	public void error(Object message) {
		log.error(String.valueOf(message));
	}

	@Override
	public void error(Object message, Throwable t) {
		log.error(String.valueOf(message), t);
	}

	@Override
	public void fatal(Object message) {
		log.error(String.valueOf(message));
	}

	@Override
	public void fatal(Object message, Throwable t) {
		log.error(String.valueOf(message), t);
	}

}
