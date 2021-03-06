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
package com.gf.components.log.slf4j;

import org.slf4j.Logger;

import com.gf.log.Log;

public class LogImpl implements Log {
	
	private final Logger log;

	public LogImpl(Logger log) {
		super();
		this.log = log;
	}

	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return log.isErrorEnabled();
	}

	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}

	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	public void trace(Object message) {
		log.trace(String.valueOf(message));
	}

	public void trace(Object message, Throwable t) {
		log.trace(String.valueOf(message), t);
	}

	public void debug(Object message) {
		log.debug(String.valueOf(message));
	}

	public void debug(Object message, Throwable t) {
		log.debug(String.valueOf(message), t);
	}

	public void info(Object message) {
		log.info(String.valueOf(message));
	}

	public void info(Object message, Throwable t) {
		log.info(String.valueOf(message), t);
	}

	public void warn(Object message) {
		log.warn(String.valueOf(message));
	}

	public void warn(Object message, Throwable t) {
		log.warn(String.valueOf(message), t);
	}

	public void error(Object message) {
		log.error(String.valueOf(message));
	}

	public void error(Object message, Throwable t) {
		log.error(String.valueOf(message), t);
	}

	public void fatal(Object message) {
		log.error(String.valueOf(message));
	}

	public void fatal(Object message, Throwable t) {
		log.error(String.valueOf(message), t);
	}

}
