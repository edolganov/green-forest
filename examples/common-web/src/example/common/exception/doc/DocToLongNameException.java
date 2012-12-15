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
