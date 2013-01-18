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

import com.gf.Handler;
import com.gf.Interceptor;

/**
 * Annotation for injecting context's object into handler.
 * Use it for <tt>Handlers</tt>, <tt>Interceptors</tt>, <tt>Filters</tt>.
 * <p>Example:
 * <pre>
 * 
 * &#064;Mapping(SomeAction.class)
 * public class SomeHandler extends Handler&lt;SomeAction&gt;{
 *   
 *   &#064;Inject
 *   SomeService service;
 *   
 *   &#064;Override
 *   public void invoke(SomeAction action) throws Exception {
 *     service.someMethod();
 *   }
 * }
 * </pre>
 *
 * @author Evgeny Dolganov
 * @see com.gf.core.Engine#addToContext(Object)
 * @see Handler
 * @see Interceptor
 * @see Filter
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

}
