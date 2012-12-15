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

import java.util.HashMap;
import java.util.Map;

import com.gf.util.Util;

/**
 * Data container and type for handlers's mapping.
 * Extend this class for your own container type, and create <tt>Handler</tt> for handle it.
 * <p>Example:
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
	 * input data for handlers
	 */
	private I input;
	
	/**
	 * output data from handlers
	 */
	private O output;
	
	/**
	 * additional attributes
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
	 * get input data for handlers
	 */
	public I getInput() {
		return input;
	}
	
	/**
	 * analog of {@link Action#getInput()}
	 */
	public I input(){
		return getInput();
	}

	public void setInput(I input) {
		this.input = input;
	}

	/**
	 * get output data from handlers
	 */
	public O getOutput() {
		return output;
	}

	/**
	 * set output data from handlers
	 */
	public void setOutput(O output) {
		this.output = output;
	}
	
	/**
	 * put some additional attribute
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
	 * get additional attribute by key
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
	 * put the map of attributes
	 */
	public void putAllAttrs(Map<String, Object> attrs){
		initAttrs();
		attrs.putAll(attrs);
	}
	
	/**
	 * get all attributes
	 */
	public Map<String, Object> getAllAttrs(){
		if(attrs == null){
			return new HashMap<String, Object>();
		}
		return new HashMap<String, Object>(attrs);
	}
	
	
	@Override
    public String toString() {
		return Util.toObjectString(this)+" [input=" + input + ", output=" + output + "]";
    }

}
