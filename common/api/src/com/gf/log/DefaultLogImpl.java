package com.gf.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultLogImpl implements Log {
	
	private static final Level WARN_LEVEL = Level.WARNING;
	private static final Level TRACE_LEVEL = Level.FINEST;
	private static final Level INFO_LEVEL = Level.INFO;
	private static final Level ERROR_LEVEL = Level.SEVERE;
	private static final Level FATAL_LEVEL = Level.SEVERE;
	private static final Level DEBUG_LEVEL = Level.FINE;
	
	private final Logger log;

	public DefaultLogImpl(Logger log) {
		super();
		this.log = log;
	}

	@Override
	public boolean isDebugEnabled() {
		return log.isLoggable(DEBUG_LEVEL);
	}

	@Override
	public boolean isErrorEnabled() {
		return log.isLoggable(ERROR_LEVEL);
	}

	@Override
	public boolean isFatalEnabled() {
		return log.isLoggable(FATAL_LEVEL);
	}

	@Override
	public boolean isInfoEnabled() {
		return log.isLoggable(INFO_LEVEL);
	}

	@Override
	public boolean isTraceEnabled() {
		return log.isLoggable(TRACE_LEVEL);
	}

	@Override
	public boolean isWarnEnabled() {
		return log.isLoggable(WARN_LEVEL);
	}

	@Override
	public void trace(Object message) {
		log.log(TRACE_LEVEL, String.valueOf(message));
	}

	@Override
	public void trace(Object message, Throwable t) {
		log.log(TRACE_LEVEL, String.valueOf(message), t);
	}

	@Override
	public void debug(Object message) {
		log.log(DEBUG_LEVEL, String.valueOf(message));
		
	}

	@Override
	public void debug(Object message, Throwable t) {
		log.log(DEBUG_LEVEL, String.valueOf(message), t);
	}

	@Override
	public void info(Object message) {
		log.log(INFO_LEVEL, String.valueOf(message));
	}

	@Override
	public void info(Object message, Throwable t) {
		log.log(INFO_LEVEL, String.valueOf(message), t);
	}

	@Override
	public void warn(Object message) {
		log.log(WARN_LEVEL, String.valueOf(message));
	}

	@Override
	public void warn(Object message, Throwable t) {
		log.log(WARN_LEVEL, String.valueOf(message), t);
	}

	@Override
	public void error(Object message) {
		log.log(ERROR_LEVEL, String.valueOf(message));
	}

	@Override
	public void error(Object message, Throwable t) {
		log.log(ERROR_LEVEL, String.valueOf(message), t);
	}

	@Override
	public void fatal(Object message) {
		log.log(FATAL_LEVEL, String.valueOf(message));
	}

	@Override
	public void fatal(Object message, Throwable t) {
		log.log(FATAL_LEVEL, String.valueOf(message), t);
	}

}
