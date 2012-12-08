package ex07_many_actions_for_one_handler;

import actions.OtherAction;
import actions.SomeAction;

import com.gf.Action;
import com.gf.Handler;
import com.gf.annotation.Mapping;


@Mapping({SomeAction.class, OtherAction.class})
public class CommonHandler extends Handler<Action<?,?>>{

	@Override
	public void invoke(Action<?, ?> action) throws Exception {
		
		log.info("handle action "+action);
		
	}

}
