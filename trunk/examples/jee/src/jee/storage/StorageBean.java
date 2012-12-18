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
package jee.storage;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gf.components.jee.ActionServiceBean;
import com.gf.components.jee.SimpleTransactionManager;
import com.gf.core.Engine;
import com.gf.service.ActionService;

import example.storage.Storage;

@Stateless(name="Storage")
@Local(Storage.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class StorageBean extends ActionServiceBean implements Storage {
	
    @PersistenceContext(name="defaultEntityManager")
    EntityManager entityManager;
    
    @Resource
    SessionContext sessionContext;


	public ActionService createActionService() {
		
		//create storage's engine
		Engine engine = new Engine("Storage Engine");
		
		//init
		engine.addToContext(entityManager);
		engine.addToContext(sessionContext);
		engine.putFilter(SimpleTransactionManager.class);
		engine.scanForAnnotations(this.getClass());
		
		return engine;
	}

}
