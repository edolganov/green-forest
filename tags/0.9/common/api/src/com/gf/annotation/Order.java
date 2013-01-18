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
package com.gf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.gf.Filter;
import com.gf.Interceptor;

/**
 * Annotation for ordering list of <tt>Interceptors</tt> or <tt>Filters</tt>.
 * <p>Example:
 * <pre>
 * &#064;Order(1)
 * &#064;Mapping(SomeAction.class)
 * public class InterceptorA extends Interceptor&lt;SomeAction&gt;{ ... }
 * 
 * &#064;Order(-1)
 * &#064;Mapping(SomeAction.class)
 * public class InterceptorB extends Interceptor&lt;SomeAction&gt;{ ... }
 * 
 * &#064;Mapping(SomeAction.class)
 * public class InterceptorC extends Interceptor&lt;SomeAction&gt;{ ... }
 * 
 * //the order is: InterceptorB, InterceptorA, InterceptorC
 * </pre>
 *
 * @author Evgeny Dolganov
 * @see Interceptor
 * @see Filter
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {
	
	/**
	 * Order for types without &#064;Order annotation. It's ({@link Integer#MAX_VALUE} - 1)
	 */
	public static final int DEFAULT_ORDER = Integer.MAX_VALUE - 1;
	
	/**
	 * Order for Green-Forest components.
	 * You can use it for your own types if you want.
	 */
	public static final int SYSTEM_ORDER = -100;
	
	/**
	 * Order value. From {@link Integer#MIN_VALUE} to {@link Integer#MAX_VALUE}
	 */
    int value() default DEFAULT_ORDER;

}
