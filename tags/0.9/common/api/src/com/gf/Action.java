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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.gf.util.Util;

/**
 * <tt>Action</tt> presents some atomic logic operation (like function in interface)
 * and contains input and output data.
 * 
 * <p>For example we have Doc class for presenting some document object. 
 * <br>So in our app we can create actions like
 * <tt>CreateDoc</tt>, <tt>RenameDoc</tt>, <tt>DeleteDoc</tt> for working with documents.
 * And <tt>CreateDoc</tt> for example has input String (name of the doc)
 * and output Doc (created object):
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
 * @param <I> input data
 * @param <O> output data
 * 
 * 
 * @author Evgeny Dolganov
 * @see Handler
 * @see Interceptor
 * @see Filter
 * @see com.gf.core.Engine
 *
 */
public abstract class Action<I, O> {
	
	/**
	 * Input data for handlers
	 */
	protected I input;
	
	/**
	 * Output data from handlers
	 */
	protected O output;
	
	/**
	 * Additional attributes
	 */
	private Map<String, Object> attrs;
	
	public Action() {
		super();
	}

	public Action(I input) {
		super();
		this.input = input;
	}
	
	/**
	 * Get input data for handlers
	 */
	public I getInput() {
		return input;
	}
	
	/**
	 * Analog of {@link Action#getInput()}
	 */
	public I input(){
		return getInput();
	}

	public void setInput(I input) {
		this.input = input;
	}

	/**
	 * Get output data from handlers
	 */
	public O getOutput() {
		return output;
	}

	/**
	 * Set output data from handlers
	 */
	public void setOutput(O output) {
		this.output = output;
	}
	
	/**
	 * Put some additional attribute
	 * @param key key for attribute
	 * @param value value object or <tt>null</tt>
	 * @return the previous value associated with key, or <tt>null</tt> if there was no mapping for key. 
	 * (A <tt>null</tt> return can also indicate that the attributes previously associated null with key.)
	 */
	public Object putAttr(String key, Object val){
		initAttrs();
		return attrs.put(key, val);
	}

	private void initAttrs() {
		if(attrs == null){
			attrs = new HashMap<String, Object>();
		}
	}
	
	/**
	 * Get additional attribute by key
	 * @return the value to which the specified key is mapped, 
	 * or <tt>null</tt> if this attributes contains no mapping for the key
	 */
	public Object getAttr(String key){
		if(attrs == null){
			return null;
		}
		return attrs.get(key);
	}
	
	/**
	 * Returns <tt>true</tt> if attributes contains a mapping for the specified key. 
	 */
	public boolean containsAttr(String key){
		if(attrs == null){
			return false;
		}
		return attrs.containsKey(key);
	}
	
	/**
	 * Remove attribute by key
	 * @return the previous attribute associated with key, or null if there was no mapping for key.
	 */
	public Object removeAttr(String key){
		if(attrs == null){
			return null;
		}
		return attrs.remove(key);
	}
	
	/**
	 * put the map of attributes
	 */
	public void putAllAttrs(Map<String, Object> attrs){
		initAttrs();
		attrs.putAll(attrs);
	}
	
	/**
	 * Get all attributes
	 */
	public Map<String, Object> getAllAttrs(){
		if(attrs == null){
			return Collections.emptyMap();
		}
		return new HashMap<String, Object>(attrs);
	}
	
	
	@Override
    public String toString() {
		return Util.toObjectString(this)+" [input=" + input + ", output=" + output + "]";
    }

}
