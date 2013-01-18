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
package example.web;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class ServletConfigMock implements ServletConfig {
	
	public HashMap<String, String> initPatams = new HashMap<String, String>();

	public String getServletName() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInitParameter(String name) {
		return initPatams.get(name);
	}

	public Enumeration getInitParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
