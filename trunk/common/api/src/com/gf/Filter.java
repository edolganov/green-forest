package com.gf;

import com.gf.annotation.Order;
import com.gf.service.FilterChain;
import com.gf.service.InvocationContextService;


/**
 * Use <tt>Filter</tt> for handle every <tt>Action</tt> before <tt>Interceptors</tt> and targeted <tt>Handler</tt>.
 * 
 * <p><b>Note:</b> 
 * <ul>
 *   <li><tt>Filter</tt> handle every <tt>Action</tt> unlike <tt>Interceptor</tt> or <tt>Handler</tt>.</li>
 *   <li>If there are few filters use <tt>&#064;Order</tt> annotaion for to determine the filters order.</li>
 * </ul>
 * 
 * <p>The order of processing:
 * <ul>
 * <li>Filters</li>
 * <li>Interceptors</li>
 * <li>Handler</li>
 * </ul>
 * <p>Interceptors and Handlers can call <tt>subInvoke</tt> method.
 * <br>In this case the order of processing:
 * <ul>
 * <li>Filters <b>(was called only once)</b></li>
 * <li>Interceptors
 * 		<ul>
 * 			<li>Sub Interceptors</li>
 * 			<li>Sub Handler</li>
 * 		</ul>
 * </li>
 * <li>Handler
 * 		<ul>
 * 			<li>Sub Interceptors</li>
 * 			<li>Sub Handler</li>
 * 		</ul>
 * </li>
 * </ul>
 * 
 * <p><p>Example:
 * <pre>
 * //filter
 * public class SomeFilter extends Filter {
 * 
 *   public void invoke(Action&lt;?, ?&gt; action, FilterChain chain) throws Exception {
 *     
 *     Date begin = new Date();
 *     log.info("begin: "+begin);
 *     
 *     try {
 *       //next handler
 *       chain.doNext();
 *     }finally {
 *       long worktime = System.currentTimeMillis() - begin.getTime();
 *       log.info("worktime: "+worktime+"ms");
 *     }
 *   }
 * }
 * 
 * //handler
 * &#064;Mapping(SomeAction.class)
 * public class SomeHandler extends Handler&lt;SomeAction&gt;{
 * 
 *   public void invoke(SomeAction action) throws Exception {
 *     String input = action.input();
 *     action.setOutput(input);
 *   }
 * }
 * 
 * //engine
 * Engine engine = new Engine();
 * engine.putHandler(SomeHandler.class);
 * engine.putFilter(SomeFilter.class);
 * engine.invoke(new SomeAction());
 * </pre>
 * 
 *
 * @author Evgeny Dolganov
 * @see Order
 * @see Action
 * @see Handler
 * @see Interceptor
 * @see com.gf.core.Engine
 */
public abstract class Filter extends InvocationObject {
	
	private InvocationContextService invocationContext;
	
	/**
	 * Process the action.
	 * @param chain interface to call next filter, interceptor or handler
	 */
	public abstract void invoke(Action<?,?> action, FilterChain chain) throws Exception;
	
	/**
	 * Add some object to current invocation context.
	 * So this object can be injected into next filters, interceptors, handlers.
	 * <p>Example:
	 * <pre>
	 * //filter
	 * public class SomeFilter extends Filter {
	 *   
	 *   &#064;Override
	 *   public void invoke(Action&lt;?, ?&gt; action, FilterChain chain) throws Exception {
	 *     
	 *     Connection connection = getConnection();
	 *     addToInvocationContext(connection);
	 *     
	 *     try {
	 *       chain.doNext();
	 *     }finally {
	 *       connection.close();
	 *     }
	 *     
	 *   }
	 * 
	 * }
	 * 
	 * &#064;Mapping(SomeAction.class)
	 * public class SomeHandler extends Handler&lt;SomeAction&gt;{
	 *   
	 *   &#064;Inject
	 *   Connection connection; //<------------ object from filter
	 * 
	 *   &#064;Override
	 *   public void invoke(SomeAction action) throws Exception {
	 *     
	 *     Statement statement = connection.createStatement();
	 *     ResultSet rs = statement.executeQuery("select count(*) from Users");
	 *     rs.next();
	 *     long count = rs.getLong(1);
	 *     action.setOutput(count);
	 *     rs.close();
	 *     
	 *   }
	 * 
	 * }
	 * </pre>
	 */
	protected void addToInvocationContext(Object ob){
		invocationContext.addToInvocationContext(ob);
	}

	public void setInvocationContext(InvocationContextService invocationContext) {
		this.invocationContext = invocationContext;
	}

}
