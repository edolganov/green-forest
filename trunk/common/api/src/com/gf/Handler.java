package com.gf;

import com.gf.annotation.Mapping;




/**
 * Use <tt>Handler</tt> for create an atomic logic "function".
 * The input attribute for this "function" is <tt>Action</tt>'s input data.
 * Set output data to <tt>Action</tt>'s output.
 * <p>Example:
 * <pre>
 * //action
 * public class SomeAction extends Action<String, String>{
 * 
 *   public SomeAction(String input) {
 *     super(input);
 *   }
 * }
 * 
 * //handler
 * &#064;Mapping(SomeAction.class)
 * public class SomeActonHandler extends Handler&lt;SomeAction&gt;{
 * 
 *   public void invoke(SomeAction action) throws Exception {
 *     String input = action.getInput();
 *     log.info("input is "+input);
 *     action.setOutput(input);
 *   }
 * }
 * 
 * //engine
 * Engine engine = new Engine();
 * engine.putHandler(SomeActonHandler.class);
 * String result = engine.invoke(new SomeAction("some data"));
 * </pre>
 * 
 * For call other handler from current use <tt>subInvoke(Action)</tt> method.
 * <p>Example:
 * <pre>
 * &#064;Mapping(SomeAction.class)
 * public class SomeHandler extends Handler&lt;SomeAction&gt;{
 * 
 *   public void invoke(SomeAction action) throws Exception {
 *     String input = action.input();
 *     boolean valid = subInvoke(new OtherAction(input)); //<------ subInvoke!
 *     if(valid){
 *       action.setOutput("valid");
 *      } else {
 *        action.setOutput("error!");
 *      }
 *    }
 *  }
 *  
 *  //engine
 *  Engine engine = new Engine();
 *  engine.putHandler(SomeHandler.class);
 *  engine.putHandler(OtherHandler.class);
 *  String result = engine.invoke(new SomeAction("test"));
 *  </pre>
 *  
 * @author Evgeny Dolganov
 * @see Mapping
 * @see Action
 * @see Interceptor
 * @see Filter
 * @see com.gf.core.Engine
 */
public abstract class Handler<T extends Action<?,?>> extends MappingObject {
	
	
	/**
	 * Process the action.
	 */
	public abstract void invoke(T action) throws Exception;
	
}
