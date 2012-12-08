package ex05_no_mapping_annotation;

import actions.SomeAction;

import com.gf.Handler;


//@Mapping(SomeAction.class)
public class HandlerWithNoMapping extends Handler<SomeAction>{

	@Override
	public void invoke(SomeAction action) throws Exception {
		//...
	}

}
