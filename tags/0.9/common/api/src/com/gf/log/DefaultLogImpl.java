/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

	public boolean isDebugEnabled() {
		return log.isLoggable(DEBUG_LEVEL);
	}

	public boolean isErrorEnabled() {
		return log.isLoggable(ERROR_LEVEL);
	}

	public boolean isFatalEnabled() {
		return log.isLoggable(FATAL_LEVEL);
	}

	public boolean isInfoEnabled() {
		return log.isLoggable(INFO_LEVEL);
	}

	public boolean isTraceEnabled() {
		return log.isLoggable(TRACE_LEVEL);
	}

	public boolean isWarnEnabled() {
		return log.isLoggable(WARN_LEVEL);
	}

	public void trace(Object message) {
		log.log(TRACE_LEVEL, String.valueOf(message));
	}

	public void trace(Object message, Throwable t) {
		log.log(TRACE_LEVEL, String.valueOf(message), t);
	}

	public void debug(Object message) {
		log.log(DEBUG_LEVEL, String.valueOf(message));
		
	}

	public void debug(Object message, Throwable t) {
		log.log(DEBUG_LEVEL, String.valueOf(message), t);
	}

	public void info(Object message) {
		log.log(INFO_LEVEL, String.valueOf(message));
	}

	public void info(Object message, Throwable t) {
		log.log(INFO_LEVEL, String.valueOf(message), t);
	}

	public void warn(Object message) {
		log.log(WARN_LEVEL, String.valueOf(message));
	}

	public void warn(Object message, Throwable t) {
		log.log(WARN_LEVEL, String.valueOf(message), t);
	}

	public void error(Object message) {
		log.log(ERROR_LEVEL, String.valueOf(message));
	}

	public void error(Object message, Throwable t) {
		log.log(ERROR_LEVEL, String.valueOf(message), t);
	}

	public void fatal(Object message) {
		log.log(FATAL_LEVEL, String.valueOf(message));
	}

	public void fatal(Object message, Throwable t) {
		log.log(FATAL_LEVEL, String.valueOf(message), t);
	}

}
