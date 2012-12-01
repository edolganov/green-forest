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
	
    @PersistenceContext(name="DefaultDS")
    EntityManager entityManager;
    
    @Resource
    SessionContext sessionContext;


	@Override
	public ActionService createActionService() {
		
		//create storage's engine
		Engine engine = new Engine("Storage Engine");
		
		//init
		engine.addToContext(entityManager);
		engine.addToContext(sessionContext);
		engine.putFilter(SimpleTransactionManager.class);
		engine.scanPackageForAnnotations(this);
		
		return engine;
	}

}
