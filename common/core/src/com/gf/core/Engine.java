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
package com.gf.core;

import java.util.Collection;
import java.util.Properties;

import com.gf.Action;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.annotation.Inject;
import com.gf.config.ConfigKey;
import com.gf.core.action.ActionServiceImpl;
import com.gf.core.config.ConfigServiceImpl;
import com.gf.core.context.ContextServiceImpl;
import com.gf.core.context.StaticContext;
import com.gf.core.deploy.DeployServiceImpl;
import com.gf.core.deploy.ResourseService;
import com.gf.exception.ExceptionWrapper;
import com.gf.exception.config.EmptyClassException;
import com.gf.exception.config.GetConfigValueException;
import com.gf.exception.config.ParsePropertiesException;
import com.gf.exception.deploy.NoMappingAnnotationException;
import com.gf.exception.deploy.NotOneHandlerException;
import com.gf.exception.invoke.InvocationException;
import com.gf.extra.scan.ClassScanner;
import com.gf.key.scan.ClassScannerKey;
import com.gf.service.ActionService;
import com.gf.service.ConfigService;
import com.gf.service.ContextService;
import com.gf.service.DeployService;

/**
 * <tt>Engine</tt> is the central class of <tt>"Green-Forest Framework"</tt>.
 * <br>Use it for create a isolated logic element with "action-handler" API:
 * <p>
 * <pre>
 * Engine engine = new Engine();
 * engine.putHandler(SomeActonHandler.class);
 * String result = engine.invoke(new SomeAction("some data"));
 * 
 * SomeAction otherAction = new SomeAction("other data");
 * engine.invoke(otherAction);
 * String otherResult = otherAction.getOutput();
 * </pre>
 * <p>
 * 
 * @author Evgeny Dolganov
 * @see Action
 * @see Handler
 * @see Interceptor
 * @see Filter
 *
 */
public class Engine implements ActionService, DeployService, ConfigService, ContextService {
	
	private String name;
	private ConfigService config;
	private DeployService deploy;
	private ActionService actions;
	private ContextService context;
	
	/**
	 * Create new instance of <tt>Engine</tt> with empty name.
	 * <p>Use engine's <tt>name</tt> for any information reason (for logging for example).
	 */
	public Engine() {
		this(null);
	}
	
	/**
	 * Create new instance of <tt>Engine</tt> with <tt>name</tt>
	 * <p>Use engine's <tt>name</tt> for any information reason (for logging for example).
	 */
	public Engine(String name) {

		this.name = name;
		config = new ConfigServiceImpl();
		deploy = new DeployServiceImpl(config);
		context = new ContextServiceImpl();
		actions = new ActionServiceImpl(
				this,
				config, 
				(ResourseService)deploy,
				(StaticContext)context);
		
	}

	/**
	 * Get current name of this <tt>Engine</tt>.
	 * <p>Use engine's <tt>name</tt> for any information reason (for logging for example).
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set new name for this <tt>Engine</tt>.
	 * <p>Use engine's <tt>name</tt> for any information reason (for logging for example).
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Handle the {@link Action} object with this <tt>Engine</tt>.
	 * <br>Use {@link Handler}, {@link Interceptor}, {@link Filter} for this.
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
	 * @return <tt>Action</tt> output object.
	 * <br><b>Note:</b> This return value is the equivalent of code {@code action.getOutput()} after the invoke method.
	 * @throws InvocationException <tt>Engine</tt>'s processing exception
	 * @throws ExceptionWrapper wrapper of non-runtime <tt>Exception</tt> from handler's body
	 * @throws RuntimeException runtime <tt>Exception</tt> from handler's body
	 * @see #invokeUnwrap(Action)
	 * @see Action
	 * @see Handler
	 * @see Interceptor
	 * @see Filter
	 */
	public <I, O> O invoke(Action<I, O> action) 
			throws InvocationException, ExceptionWrapper, RuntimeException {
		return (O)actions.invoke(action);
	}
	
	/**
	 * This method is {@link Engine#invoke(Action)} analog with unwrap non-runtime exceptions.
	 * <p>Example:
	 * <pre>
	 * &#064;Mapping(SomeAction.class)
	 * public class ActionHandlerWithException extends Handler&lt;SomeAction&gt;{
	 * 
	 *   public void invoke(SomeAction action) throws Exception {
	 *     //not-runtime exception
	 *     throw new Exception("test expection");
	 *   }
	 *   
	 * }
	 * 
	 * ...
	 * 
	 * //use invokeUnwrap
	 * try {
	 *   engine.invokeUnwrap(new SomeAction());
	 * }catch (Exception e) {
	 *   System.out.println(e);
	 * }
	 * 
	 * //use invoke
	 * engine.invoke(new SomeAction()); //throws ExceptionWrapper
	 * </pre>
	 * 
	 * @see #invoke(Action)
	 */
	public <I, O> O invokeUnwrap(Action<I, O> action) 
			throws InvocationException, Exception {
		return (O)actions.invokeUnwrap(action);
	}
	
	/**
	 * Put the <tt>Handler</tt> class into this <tt>Engine</tt>.
	 */
	public void putHandler(Class<? extends Handler<?>> clazz)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.putHandler(clazz);
	}

	/**
	 * Put the <tt>Interceptor</tt> class into this <tt>Engine</tt>.
	 */
	public void putInterceptor(Class<? extends Interceptor<?>> clazz)
			throws NoMappingAnnotationException {
		deploy.putInterceptor(clazz);
	}

	/**
	 * Put the <tt>Filter</tt> class into this <tt>Engine</tt>.
	 */
	public void putFilter(Class<? extends Filter> clazz) {
		deploy.putFilter(clazz);
	}
	
	/**
	 * Scan packages and put all handlers, interceptors, filters into this <tt>Engine</tt>.
	 * <p>Example:
	 * <pre>
	 * //handler
	 * package some.package.subPackage;
	 * &#064;Mapping(SomeAction.class)
	 * public class SomeHandler extends Handler&lt;SomeAction&gt;{...}
	 * 
	 * //engine
	 * Engine engine = new Engine();
	 * engine.scanForAnnotations("some.package");
	 * engine.invoke(new SomeAction());
	 * </pre>
	 * 
	 * <p><b>Note:</b> For some Application Servers (JBoss AS for example)
	 * you need to setup {@link com.gf.components.reflections.ReflectionsScanner}.
	 * Or you can use your own {@link ClassScanner}.
	 * <p>
	 * @param packageName root package for scan (example: "com.some.package")
	 * @see ClassScanner
	 * @see ClassScannerKey
	 */
	public void scanForAnnotations(String packageName)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.scanForAnnotations(packageName);
	}
	
	/**
	 * Analog for {@link #scanForAnnotations(String)}: <tt>scanForAnnotations(clazz.getPackage().getName())</tt>
	 * 
	 * <p><b>Note:</b> For some Application Servers (JBoss AS for example)
	 * you need to setup {@link com.gf.components.reflections.ReflectionsScanner}.
	 * Or you can use your own {@link ClassScanner}.
	 * <p>
	 */
	public void scanForAnnotations(Class<?> clazz)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.scanForAnnotations(clazz);
	}
	
	
	/**
	 * Analog of multi call of {@link #scanForAnnotations(String)}.
	 * For example for JavaBean logic.
	 * 
	 * <p><b>Note:</b> For some Application Servers (JBoss AS for example)
	 * you need to setup {@link com.gf.components.reflections.ReflectionsScanner}.
	 * Or you can use your own {@link ClassScanner}.
	 * <p>
	 */
	public void setScanForAnnotations(Collection<String> packageNames)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.setScanForAnnotations(packageNames);
	}
	
	
	/**
	 * Set config value by key type. You can create own config keys for using in handlers.
	 * <br>Example of using:
	 * <pre>
	 * //key
	 * public class SomeConfigKey extends ConfigKey&lt;String&gt;{
	 * 
	 *   public boolean hasDefaultValue() {
	 *     return true;
	 *   }
	 *   
	 *   public String getDefaultValue() throws Exception {
	 *     return "some default value";
	 *   }
	 * }
	 * 
	 * //handler
	 * &#064;Mapping(SomeAction.class)
	 * public class SomeHandler extends Handler&lt;SomeAction&gt;{
	 * 
	 *   public void invoke(SomeAction action) throws Exception {
	 *     String value = config.getConfig(new SomeConfigKey());
	 *     log.info(value);
	 *   }
	 * }
	 * 
	 * //engine
	 * Engine engine = new Engine();
	 * engine.putHandler(SomeHandler.class);
	 * engine.setConfig(SomeConfigKey.class, "some value");
	 * engine.invoke(new SomeAction());
	 * </pre>
	 * @param keyType not null type
	 * @param value some value for this config type (can be null)
	 * @throws EmptyClassException if <tt>keyType</tt> is null
	 * @see ConfigKey
	 */
	public <T> void setConfig(Class<? extends ConfigKey<T>> keyType, T value) throws EmptyClassException {
		config.setConfig(keyType, value);
	}
	
	/**
	 * Get config value or default value or exception if key doesn't have default value.
	 * <br>Example:
	 * <pre>
	 * //key with default value
	 * public class SomeConfigKey extends ConfigKey&lt;String&gt;{
	 * 
	 *   public boolean hasDefaultValue() {
	 *     return true;
	 *   }
	 *   
	 *   public String getDefaultValue() throws Exception {
	 *     return "some default value";
	 *   }
	 * }
	 * 
	 * //key without default value
	 * public class OtherConfigKey extends ConfigKey&lt;String&gt;{
	 * 
	 *   public boolean hasDefaultValue() {
	 *     return false;
	 *   }
	 * }
	 * 
	 * //engine
	 * Engine engine = new Engine();
	 * 
	 * String val1 = engine.getConfig(new SomeConfigKey());
	 * System.out.println(val1); //<--------- print "some default value"
	 *     
	 * engine.setConfig(SomeConfigKey.class, "some value");
	 * String val2 = engine.getConfig(new SomeConfigKey());
	 * System.out.println(val2); //<--------- print "some value"
	 *     
	 * engine.getConfig(new OtherConfigKey()); //<---------- get GetConfigValueException
	 * </pre>
	 * 
	 * @see ConfigKey
	 */
	public <T> T getConfig(ConfigKey<T> key) throws GetConfigValueException {
		return (T) config.getConfig(key);
	}

	/**
	 * Get boolean value of <tt>Boolean</tt> config key. If value is <tt>null</tt> return false.
	 * Anolog of {@link #getConfig(ConfigKey)}: <tt>Boolean.TRUE.equals(getConfig(key))</tt>
	 * 
	 * @see ConfigKey
	 */
	public boolean isTrueConfig(ConfigKey<Boolean> key) throws GetConfigValueException {
		return config.isTrueConfig(key);
	}
	
	/**
	 * Parse config values from Properties. Key must be a config key's <tt>Class</tt> string.
	 * <br>For example:
	 * <pre>
	 * //file config.properties
	 * some.package.SomeConfigKey=value 1
	 * some.package.OtherConfigKey=value 2
	 * com.gf.key.TraceHandlers=true 
	 * 
	 * //props
	 * InputStream is = getResourceAsStream("config.properties");
	 * Properties props = new Properties();
	 * props.load(is);
	 * 
	 * //engine
	 * Engine engine = new Engine();
	 * engine.setConfigValues(props);
	 * String val1 = engine.getConfig(new SomeConfigKey());
	 * String val2 = engine.getConfig(new OtherConfigKey());
	 * boolean val3 = engine.isTrueConfig(new TraceHandlers());
	 * System.out.println(val1); //print "value 1"
	 * System.out.println(val2); //print "value 2"
	 * System.out.println(val3); //print "true"
	 * </pre>
	 * 
	 * @see ConfigKey
	 */
	public void setConfigValues(Properties props) throws ParsePropertiesException {
		config.setConfigValues(props);
	}
	
	/**
	 * Add some object to current engine context.
	 * So this object can be injected into filters, interceptors, handlers.
	 * <p>Example:
	 * <pre>
	 * //engine
	 * SomeService someService = new SomeService();
	 * OtherService otherService = new OtherService();
	 * Engine engine = new Engine();
	 * engine.addToContext(someService);
	 * engine.addToContext(otherService);
	 * engine.putHandler(SomeHandler.class);
	 * engine.invoke(new SomeAction());
	 * 
	 * //handler
	 * &#064;Mapping(SomeAction.class)
	 * public class SomeHandler extends Handler&lt;SomeAction&gt;{
	 *   
	 *   &#064;Inject
	 *   SomeService service;
	 *   
	 *   &#064;Inject
	 *   OtherService otherService;
	 *   
	 *   &#064;Override
	 *   public void invoke(SomeAction action) throws Exception {
	 *   
	 *     service.someMethod();
	 *     otherService.invoke(new OtherAction()); //it can be an another Engine. Why not?
	 *     
	 *   }
	 * }
	 * </pre>
	 * 
	 * @see Inject
	 */
	public void addToContext(Object object) {
		context.addToContext(object);
	}
	
	/**
	 * Analog of multi call of {@link #addToContext(Object)}.
	 * For example for JavaBean logic.
	 */
	public void setContextObjects(Collection<Object> objects) {
		context.setContextObjects(objects);
	}

	/**
	 * Analog of multi call of {@link #putHandler(Class)}.
	 * For example for JavaBean logic.
	 */
	public void setHandlerTypes(Collection<Class<? extends Handler<?>>> handlerTypes)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.setHandlerTypes(handlerTypes);
	}

	/**
	 * Analog of multi call of {@link #putInterceptor(Class)}.
	 * For example for JavaBean logic.
	 */
	public void setInterceptorTypes(Collection<Class<? extends Interceptor<?>>> interceptorTypes)
			throws NoMappingAnnotationException{
		deploy.setInterceptorTypes(interceptorTypes);
	}

	/**
	 * Analog of multi call of {@link #putFilter(Class)}.
	 * For example for JavaBean logic.
	 */
	public void setFilterTypes(Collection<Class<? extends Filter>> filterTypes) {
		deploy.setFilterTypes(filterTypes);
	}

	

}
