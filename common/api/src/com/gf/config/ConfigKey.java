package com.gf.config;

import com.gf.service.ConfigService;

/**
 * Abstract class for config key.
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
 * @author Evgeny Dolganov
 * @see ConfigService
 * @see com.gf.core.Engine#setConfig(Class, Object)
 * @see com.gf.core.Engine#getConfig(ConfigKey)
 *
 */
public abstract class ConfigKey<T> {
	
	/**
	 * Do this key have a default value?
	 */
	public abstract boolean hasDefaultValue();
	
	/**
	 * Override this method for return default value.
	 */
	public T getDefaultValue() throws Exception {
		return null;
	}
	
	

}
