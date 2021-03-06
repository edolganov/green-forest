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
package com.gf.key;

import com.gf.config.ConfigKey;
import com.gf.exception.invoke.InvokeDepthMaxSizeException;

/**
 * Max value of sub invokes.
 * <br>If there are more than that value - throws {@link InvokeDepthMaxSizeException}
 *
 * @author Evgeny Dolganov
 * @see InvokeDepthMaxSizeException
 *
 */
public class InvokeDepthMaxSize extends ConfigKey<Integer>{

	@Override
	public boolean hasDefaultValue() {
		return true;
	}
	
	@Override
	public Integer getDefaultValue() throws Exception {
		return 100;
	}

}
