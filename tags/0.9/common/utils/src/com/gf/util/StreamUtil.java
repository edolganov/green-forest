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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class StreamUtil {

	public static final int DEFAULT_BUFFER_SIZE = 8192;

	public static abstract class ExceptionHandler {

		public void onInputException(Exception e) throws IOException {
			defaultHandle(e);
		}

		public void onOutputException(Exception e) throws IOException {
			defaultHandle(e);
		}

		private void defaultHandle(Exception e) throws IOException {
			if (e instanceof IOException) {
				throw (IOException) e;
			}
			throw ExceptionUtil.getRuntimeExceptionOrThrowError(e);
		}
	}

	public static final void copyAndClose(InputStream in, OutputStream out)
			throws IOException {
		copy(in, out, DEFAULT_BUFFER_SIZE, true, null);
	}

	public static final void copyAndClose(InputStream in, OutputStream out,
			ExceptionHandler exceptionHandler) throws IOException {
		copy(in, out, DEFAULT_BUFFER_SIZE, true, exceptionHandler);
	}

	public static final void copyAndClose(InputStream in, OutputStream out,
			int bufferSize) throws IOException {
		copy(in, out, bufferSize, true, null);
	}

	public static final void copyAndClose(InputStream in, OutputStream out,
			int bufferSize, ExceptionHandler exceptionHandler)
			throws IOException {
		copy(in, out, bufferSize, true, exceptionHandler);
	}

	public static final void copy(InputStream in, OutputStream out,
			boolean close) throws IOException {
		copy(in, out, DEFAULT_BUFFER_SIZE, close, null);
	}

	public static final void copy(InputStream in, OutputStream out,
			boolean close, ExceptionHandler exceptionHandler)
			throws IOException {
		copy(in, out, DEFAULT_BUFFER_SIZE, close, exceptionHandler);
	}

	public static final void copy(InputStream in, OutputStream out,
			int bufferSize, boolean close) throws IOException {
		copy(in, out, bufferSize, close, null);
	}

	public static final void copy(InputStream in, OutputStream out,
			int bufferSize, boolean close, ExceptionHandler exceptionHandler)
			throws IOException {

		if (Util.isEmpty(exceptionHandler)) {
			exceptionHandler = new ExceptionHandler() {
			};
		}

		try {

			byte[] buffer = new byte[bufferSize];
			int len = 0;
			while (true) {

				// try read
				try {
					len = in.read(buffer);
					if (len < 0) {
						break;
					}
				} catch (Exception e) {
					exceptionHandler.onInputException(e);
					break;
				}

				// try write
				try {
					out.write(buffer, 0, len);
				} catch (Exception e) {
					exceptionHandler.onOutputException(e);
					break;
				}
			}

		} finally {
			if (close) {
				close(in);
				close(out);
			}
		}

	}

	public static final void close(InputStream is) {

		if (is == null) {
			return;
		}

		try {
			is.close();
		} catch (Exception e) {
			System.err.println("can't close InputStream: " + e);
		}
	}

	public static final void close(OutputStream os) {

		if (os == null) {
			return;
		}

		try {
			os.close();
		} catch (Exception e) {
			System.err.println("can't close OutputStream" + e);
		}
	}

}
