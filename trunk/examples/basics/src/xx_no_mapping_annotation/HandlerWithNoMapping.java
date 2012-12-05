package xx_no_mapping_annotation;

import com.gf.Handler;

import ex01_invoke.SomeAction;

//@Mapping(SomeAction.class)
public class HandlerWithNoMapping extends Handler<SomeAction>{

	@Override
	public void invoke(SomeAction action) throws Exception {
		//...
	}

}
