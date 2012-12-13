package com.gf;

import com.gf.config.ConfigKey;
import com.gf.log.Log;
import com.gf.log.LogFactory;
import com.gf.service.ConfigService;

/**
 * Base type for <tt>Handler</tt>, <tt>Interceptor</tt>, <tt>Filter</tt>
 *
 * @author Evgeny Dolganov
 * @see Handler
 * @see Interceptor
 * @see Filter
 *
 */
public abstract class InvocationObject {
	
	/**
	 * Default logger
	 */
	protected Log log = LogFactory.getLog(getClass());
	
	/**
	 * Config service.
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
	 * 
	 * @see ConfigKey
	 */
	protected ConfigService config;

	
	public void setConfigService(ConfigService config) {
		this.config = config;
	}
	
	
	
}
