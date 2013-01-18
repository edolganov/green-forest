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

import com.gf.Action;
import com.gf.Handler;
import com.gf.Interceptor;

/**
 * Annotation for mapping <tt>Action</tt> types to handlers.
 * Use it for <tt>Handlers</tt> and <tt>Interceptors</tt>.
 * <p>Examples:
 * <pre>
 * &#064;Mapping(SomeAction.class)
 * public class SomeActonHandler extends Handler&lt;SomeAction&gt;{ ... }
 * 
 * &#064;Mapping({OtherActionA.class, OtherActionB.class})
 * public class CommonHandler extends Handler&lt;Action&lt;?,?&gt;&gt;{ ... }
 * 
 * &#064;Mapping(Action.class)
 * public class SingleHandler extends Handler&lt;Action&lt;?,?&gt;&gt;{ ... }
 * </pre>
 *
 * @author Evgeny Dolganov
 * @see Handler
 * @see Interceptor
 * @see Action
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapping {
	
	/**
	 * Single or set of action types
	 */
    Class<?>[] value();

}
