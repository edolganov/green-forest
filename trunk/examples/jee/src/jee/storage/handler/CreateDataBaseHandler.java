package jee.storage.handler;

import java.sql.Connection;

import mybatis.mapper.DocMapper;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.CreateDataBase;
import example.storage.StorageUtil;

@Mapping(CreateDataBase.class)
public class CreateDataBaseHandler extends Handler<CreateDataBase>{
	
	@Inject
	Connection connection;
	
	@Inject
	DocMapper docMapper;

	@Override
	public void invoke(CreateDataBase action) throws Exception {
		
		if( ! tablesExists()) {
			createTables();
			action.setOutput(true);
		} else {
			action.setOutput(false);
		}
		
	}
	
	private void createTables() {
		log.info("create table 'DOC'");
		docMapper.createDocSchema(StorageUtil.DOC_NAME_SIZE);
	}

	private boolean tablesExists() throws Exception {
		return StorageUtil.tableExists("doc", connection);
	}
	
	

}
