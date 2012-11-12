package mybatis.storage;

import java.sql.Connection;

import mybatis.mapper.DocMapper;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.app.CreateOrUpdateDataBase;
import example.storage.StorageUtil;

@Mapping(CreateOrUpdateDataBase.class)
public class CreateDataBaseHandler extends Handler<CreateOrUpdateDataBase>{
	
	@Inject
	Connection connection;
	
	@Inject
	DocMapper docMapper;

	@Override
	public void invoke(CreateOrUpdateDataBase action) throws Exception {
		
		log.info("Create or update DB structure...");
		
		if( ! tablesExists()) {
			createTables();
		}
		
		log.info("Done");
		
	}
	
	private void createTables() {
		
		log.info("create table 'DOC'");
		
		docMapper.createDocSchema(StorageUtil.DOC_NAME_SIZE);
		
	}

	private boolean tablesExists() throws Exception {
		return StorageUtil.tableExists("doc", connection);
	}
	
	

}
