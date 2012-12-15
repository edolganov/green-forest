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
package com.gf.util.test.concurrent;

public abstract class ThreadRacer {
	
	public abstract void invoke() throws Exception;
	
	protected void fireAndWait(String fireEvent, String waitEvent) {
		// TODO Auto-generated method stub
		
	}
	
	protected void fire(String fireEvent) {
		// TODO Auto-generated method stub
		
	}

}
