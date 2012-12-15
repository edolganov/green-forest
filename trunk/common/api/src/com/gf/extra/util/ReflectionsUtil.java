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
package com.gf.extra.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gf.exception.invoke.InjectException;
import com.gf.exception.invoke.ObjectToInjectNotFoundException;

public class ReflectionsUtil {


	public static void injectDataFromContext(Object ob, Collection<Object> collection, 
			Class<? extends Annotation> annotationClass) throws InjectException {
		
		injectDataFromContext(ob, collection, annotationClass, true);
	}
	
	public static void injectDataFromContext(Object ob, Collection<Object> collection, 
			Class<? extends Annotation> annotationClass, boolean exceptionIfNotFound) throws InjectException {
		
		List<Field> requiredField = getRequiredFields(ob, annotationClass);
		for (Field field : requiredField) {
			
			try {
				
				field.setAccessible(true);
				
				Object oldValue = field.get(ob);
				if(oldValue != null){
					continue;
				}
				
				Object objectToInject = findObjectToInject(field, collection);
				if(objectToInject == null){
					if(exceptionIfNotFound){
						throw new ObjectToInjectNotFoundException(ob, field);
					}
				} else {
					field.set(ob, objectToInject);
				}
			
			}
			catch (InjectException e) {
				throw e;
			}
			catch (Exception e) {
				throw new InjectException("can't set value for ["+field+"] of "+ob, e);
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
