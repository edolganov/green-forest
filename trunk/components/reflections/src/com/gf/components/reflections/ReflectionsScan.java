package com.gf.components.reflections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.gf.core.scan.ClassScan;
import com.gf.log.Log;
import com.gf.log.LogFactory;
import com.google.common.collect.Multimap;

public class ReflectionsScan implements ClassScan {
	
	Log log = LogFactory.getLog(getClass());

	@Override
	public Set<Class<?>> getClasses(String packageRoot, Class<?> parentClass) {

		SubTypesScanner subTypesScanner = new SubTypesScanner();
		
		new Reflections(new ConfigurationBuilder()
        	.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageRoot)))
        	.setUrls(ClasspathHelper.forPackage(packageRoot))
        	.setScanners(subTypesScanner));
		
		Multimap<String, String> map = subTypesScanner.getStore();
		Set<Class<?>> out = findAbsoluteSubTypes(map, parentClass);
		return out;
	}

	private Set<Class<?>> findAbsoluteSubTypes(Multimap<String, String> map, Class<?> parentClass) {
		
		
		HashSet<Class<?>> out = new HashSet<Class<?>>();
		HashSet<String> processed = new HashSet<String>();
		
		Collection<String> values = map.values();
		for(String candidat : values){
			if(processed.contains(candidat)){
				continue;
			}
			try {
				Class<?> clazz = Class.forName(candidat);
				if(parentClass.isAssignableFrom(clazz)){
					out.add(clazz);
				}
			} catch (ClassNotFoundException e) {
				log.error("can't get class by name "+candidat, e);
			}
			processed.add(candidat);
		}
		return out;
	}

}
