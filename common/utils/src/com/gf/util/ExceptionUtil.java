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
