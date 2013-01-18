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
 * <tt>Handler</tt> implements logic of the {@link Action} (like interface function's implementation). 
 * It uses <tt>Action</tt> object for taking input data and setting output data. 
 * <p><b>Note:</b> <tt>Handler</tt> must contains {@link Mapping} annotation to be valid for <tt>Engine</tt>.
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
 * public class CreateDoc extends Action&lt;String, Doc&gt;{
 * 
 *   public CreateDoc(String name) {
 *     super(name);
 *   }
 * }
 * 
 * //implementation of this action
 * &#064;Mapping(CreateDoc.class)
 * public class CreateDocHandler extends Handler&lt;CreateDoc&gt;{
 * 
 *   &#064;Inject
 *   Storage storage;
 * 
 *   public void invoke(CreateDoc action) throws Exception {
 *     String name = action.getInput();
 *     Doc created = storage.createNewDoc(name);
 *     action.setOutput(created);
 *   }
 * }
 * 
 * //use example
 * Engine engine = new Engine();
 * engine.addToContext(storage);
 * engine.putHandler(CreateDocHandler.class);
 * Doc result = engine.invoke(new CreateDoc("some name"));
 * </pre>
 * 
 * For call other handler from current use {@link MappingObject#subInvoke(Action) subInvoke(Action)} method.
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
 * @see Action
 * @see Mapping
 * @see Order
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
