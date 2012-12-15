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
package com.gf;

import com.gf.annotation.Mapping;
import com.gf.annotation.Order;




/**
 * Use <tt>Handler</tt> for create an atomic logic "function".
 * The input attribute for this "function" is <tt>Action</tt>'s input data.
 * Set output data to <tt>Action</tt>'s output.
 * 
 * <p><b>Note:</b> <tt>Handler</tt> must contains <tt>&#064;Mapping</tt> annotation to be valid for <tt>Engine</tt>.
 * 
 * <p>The order of processing:
 * <ul>
 * <li>Filters</li>
 * <li>Interceptors</li>
 * <li>Handler</li>
 * </ul>
 * <p>Interceptors and Handlers can call <tt>subInvoke</tt> method.
 * <br>In this case the order of processing:
 * <ul>
 * <li>Filters <b>(was called only once)</b></li>
 * <li>Interceptors
 * 		<ul>
 * 			<li>Sub Interceptors</li>
 * 			<li>Sub Handler</li>
 * 		</ul>
 * </li>
 * <li>Handler
 * 		<ul>
 * 			<li>Sub Interceptors</li>
 * 			<li>Sub Handler</li>
 * 		</ul>
 * </li>
 * </ul>
 * 
 * <p><p>Example:
 * <pre>
 * //action
 * public class SomeAction extends Action<String, String>{
 * 
 *   public SomeAction(String input) {
 *     super(input);
 *   }
 * }
 * 
 * //handler
 * &#064;Mapping(SomeAction.class)
 * public class SomeActonHandler extends Handler&lt;SomeAction&gt;{
 * 
 *   public void invoke(SomeAction action) throws Exception {
 *     String input = action.getInput();
 *     log.info("input is "+input);
 *     action.setOutput(input);
 *   }
 * }
 * 
 * //engine
 * Engine engine = new Engine();
 * engine.putHandler(SomeActonHandler.class);
 * String result = engine.invoke(new SomeAction("some data"));
 * </pre>
 * 
 * For call other handler from current use <tt>subInvoke(Action)</tt> method.
 * <p><p>Example:
 * <pre>
 * &#064;Mapping(SomeAction.class)
 * public class SomeHandler extends Handler&lt;SomeAction&gt;{
 * 
 *   public void invoke(SomeAction action) throws Exception {
 *     String input = action.input();
 *     boolean valid = subInvoke(new OtherAction(input)); //<------ subInvoke!
 *     if(valid){
 *       action.setOutput("valid");
 *      } else {
 *        action.setOutput("error!");
 *      }
 *    }
 *  }
 *  
 *  //engine
 *  Engine engine = new Engine();
 *  engine.putHandler(SomeHandler.class);
 *  engine.putHandler(OtherHandler.class);
 *  String result = engine.invoke(new SomeAction("test"));
 *  </pre>
 *  
 * @author Evgeny Dolganov
 * @see Mapping
 * @see Order
 * @see Action
 * @see Interceptor
 * @see Filter
 * @see com.gf.core.Engine
 */
public abstract class Handler<T extends Action<?,?>> extends MappingObject {
	
	
	/**
	 * Process the action.
	 */
	public abstract void invoke(T action) throws Exception;
	
}
