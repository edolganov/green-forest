package com.gf.components.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.junit.After;
import org.junit.Before;

import com.gf.components.atomikos.jdbc.DataSourceManagerImpl;
import com.gf.util.file.FileUtil;
import com.gf.util.junit.AssertExt;

public class AbstractTest extends AssertExt {
	
	public final String sessionId = String.valueOf(System.currentTimeMillis());
	public final String tmpDirPath = "./tmp";
	
	protected SqlSessionFactory sqlSessionFactory;
	protected DataSource dataSource;
	
	@Before
	public void init() throws Exception{
		
		DataSourceManagerImpl manager = new DataSourceManagerImpl();
		manager.setUser("sa");
		manager.setPassword("");
		manager.setDriverClass("org.h2.Driver");
		manager.setUrl("jdbc:h2:"+tmpDirPath+"/db-"+sessionId);
		manager.setPoolSize(10);
		manager.init();
		
		dataSource = manager.getDataSource();
		
		TransactionFactory transactionFactory = new ManagedTransactionFactory(); 
		Environment environment =  new Environment("default-env", transactionFactory, dataSource); 
        Configuration configuration = new Configuration(environment);
        configuration.addMappers("com.gf", AbstractMapper.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        
	}
	
	@After
	public void after(){
		FileUtil.deleteDirRecursive(tmpDirPath);
	}

}
