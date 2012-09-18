package com.gf.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReflectionUtil {


	public static void injectDataFromContext(Object ob, Collection<Object> collection, Class<? extends Annotation> annotationClass) throws Exception {
		
		injectDataFromContext(ob, collection, annotationClass, true);
	}
	
	public static void injectDataFromContext(Object ob, Collection<Object> collection, Class<? extends Annotation> annotationClass, boolean exceptionIfNotFound) throws Exception {
		
		List<Field> requiredField = getRequiredFields(ob, annotationClass);
		for (Field field : requiredField) {
			Object objectToInject = findObjectToInject(field, collection);
			if(objectToInject == null){
				if(exceptionIfNotFound){
					throw new IllegalStateException("can't find object to inject by "+field+" for "+ob);
				}
			} else {
				try {
					inject(field, ob, objectToInject);
				}catch (Exception e) {
					throw new IllegalStateException("can't set value for "+field);
				}

			}
		}
	}
	

	public static List<Field> getRequiredFields(Object ob, Class<? extends Annotation> annotationClass) {
		ArrayList<Field> out = new ArrayList<Field>();
		Class<?> curClass = ob.getClass();
		while(!curClass.equals(Object.class)){
			Field[] fields = curClass.getDeclaredFields();
			for(Field candidat : fields){
				Object inject = candidat.getAnnotation(annotationClass);
				if(inject != null){
					out.add(candidat);
				}
			}
			curClass = curClass.getSuperclass();
		}
		return out;
	}
	
	public static Object findObjectToInject(Field field, Collection<Object> collection) {
		Class<?> declaringType = field.getType();
		for (Object candidat : collection) {
			if(declaringType.isAssignableFrom(candidat.getClass())){
				return candidat;
			}
		}
		return null;
	}


	public static void inject(Field field, Object ob, Object objectToInject) throws Exception {
		field.setAccessible(true);
		field.set(ob, objectToInject);
	}

}
