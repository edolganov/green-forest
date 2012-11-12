package com.gf.components.mybatis.model;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.components.mybatis.SqlSessionSettings;
import com.gf.test.action.EmptyAction;

@SqlSessionSettings(execType=ExecutorType.BATCH)
@Mapping(EmptyAction.class)
public class HandlerWithSettings extends Handler<EmptyAction> {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public void invoke(EmptyAction action) throws Exception {
		//nothing
	}

}
