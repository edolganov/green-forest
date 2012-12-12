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
	 * @see Engine#invokeUnwrap(Action)
	 * @see Action
	 * @see Handler
	 * @see Interceptor
	 * @see Filter
	 */
	@Override
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
	 * @see Engine#invoke(Action)
	 */
	@Override
	public <I, O> O invokeUnwrap(Action<I, O> action) 
			throws InvocationException, Exception {
		return (O)actions.invokeUnwrap(action);
	}
	
	/**
	 * Put the <tt>Handler</tt> class into this <tt>Engine</tt>.
	 */
	@Override
	public void putHandler(Class<? extends Handler<?>> clazz)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.putHandler(clazz);
	}

	/**
	 * Put the <tt>Interceptor</tt> class into this <tt>Engine</tt>.
	 */
	@Override
	public void putInterceptor(Class<? extends Interceptor<?>> clazz)
			throws NoMappingAnnotationException {
		deploy.putInterceptor(clazz);
	}

	/**
	 * Put the <tt>Filter</tt> class into this <tt>Engine</tt>.
	 */
	@Override
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
	 * you need to include <tt>green-forest-reflections</tt> jar (with it's dependencies) into libraries for correct work.
	 * Or you can use your own {@link ClassScanner}.
	 * <p>
	 * @param packageName root package for scan (example: "com.some.package")
	 * @see ClassScanner
	 * @see ClassScannerKey
	 */
	@Override
	public void scanForAnnotations(String packageName)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.scanForAnnotations(packageName);
	}
	
	/**
	 * Analog for {@link #scanForAnnotations(String)}: <tt>scanForAnnotations(clazz.getPackage().getName())</tt>
	 * 
	 * <p><b>Note:</b> For some Application Servers (JBoss AS for example) 
	 * you need to include <tt>green-forest-reflections</tt> jar (with it's dependencies) into libraries for correct work.
	 * Or you can use your own {@link ClassScanner}.
	 * <p>
	 */
	@Override
	public void scanForAnnotations(Class<?> clazz)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.scanForAnnotations(clazz);
	}
	
	
	/**
	 * For JavaBean logic (in Spring's xml for example).
	 * <br> Analog of multi call of {@link #scanForAnnotations(String)}.
	 * <p>Spring xml example:
	 * <pre>
	 *   &lt;bean id="appEngine" class="com.gf.core.Engine"&gt;
	 *     &lt;property name="scanForAnnotations"&gt;
	 *       &lt;list&gt;
	 *         &lt;value&gt;some.package&lt;/value&gt;
	 *       &lt;/list&gt;
	 *     &lt;/property&gt;
	 *   &lt;/bean&gt;
	 * </pre>
	 * 
	 * <p><b>Note:</b> For some Application Servers (JBoss AS for example) 
	 * you need to include <tt>green-forest-reflections</tt> jar (with it's dependencies) into libraries for correct work.
	 * Or you can use your own {@link ClassScanner}.
	 * <p>
	 */
	@Override
	public void setScanForAnnotations(Collection<String> packageNames)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.setScanForAnnotations(packageNames);
	}

	@Override
	public <T> T getConfig(ConfigKey<T> key) {
		return (T) config.getConfig(key);
	}


	@Override
	public boolean isTrueConfig(ConfigKey<Boolean> key) {
		return config.isTrueConfig(key);
	}


	@Override
	public <T> void setConfig(Class<? extends ConfigKey<T>> keyType, T value) {
		config.setConfig(keyType, value);
	}
	
	@Override
	public void setConfigValues(Properties props) {
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
	@Override
	public void addToContext(Object object) {
		context.addToContext(object);
	}
	
	@Override
	public void setContextObjects(Collection<Object> objects) {
		context.setContextObjects(objects);
	}

	@Override
	public void setHandlerTypes(Collection<Class<? extends Handler<?>>> handlerTypes)
			throws NoMappingAnnotationException, NotOneHandlerException {
		deploy.setHandlerTypes(handlerTypes);
	}

	@Override
	public void setInterceptorTypes(Collection<Class<? extends Interceptor<?>>> interceptorTypes)
			throws NoMappingAnnotationException{
		deploy.setInterceptorTypes(interceptorTypes);
	}

	@Override
	public void setFilterTypes(Collection<Class<? extends Filter>> filterTypes) {
		deploy.setFilterTypes(filterTypes);
	}

	

}
