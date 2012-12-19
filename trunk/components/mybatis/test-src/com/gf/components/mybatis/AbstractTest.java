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
import com.gf.util.FileUtil;
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
