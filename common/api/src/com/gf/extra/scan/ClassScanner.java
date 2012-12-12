package com.gf.extra.scan;

import java.util.Set;

import com.gf.key.scan.ClassScannerKey;

/**
 * Interface for package scanning. Used for {@link com.gf.core.Engine#scanForAnnotations(String)} logic.
 * <br>There are two default implementations: 
 * <ul>
 * 	<li>Engine's default: for standalone app, Tomcat Web Server</li>
 *  <li><tt>green-forest-reflections</tt> (proxy for <a href='http://code.google.com/p/reflections/'>Reflections tool</a>): for JBoss AS</li>
 * </ul>
 * 
 * You can use own scanner type by setting it to engine with config key {@link ClassScannerKey}:
 * <pre>
 * Engine engine = new Engine();
 * engine.setConfig(ClassScannerKey.class, YourScannerType.class);
 * engine.scanPackageForAnnotations("some.package");
 * </pre>
 *
 * @author Evgeny Dolganov
 * @see ClassScannerKey
 */
public interface ClassScanner {
	
	/**
	 * get children for parent class in packageRoot space
	 * @param packageRoot some package (for example "com.my.package")
	 * @param parentClass some parent type (without <tt>Object</tt> type)
	 * @return set of children classes or null
	 */
	Set<Class<?>> getClasses(String packageRoot, Class<?> parentClass);

}
