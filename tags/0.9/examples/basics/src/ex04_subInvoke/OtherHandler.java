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
package ex04_subInvoke;

import actions.OtherAction;

import com.gf.Handler;
import com.gf.annotation.Mapping;

@Mapping(OtherAction.class)
public class OtherHandler extends Handler<OtherAction>{

	@Override
	public void invoke(OtherAction action) throws Exception {
		
		String input = action.input();
		
		if(input == null || input.length() == 0){
			action.setOutput(false);
		} else {
			action.setOutput(true);
		}
		
	}

}
