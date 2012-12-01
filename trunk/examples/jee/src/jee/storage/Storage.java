package jee.storage;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.gf.components.jee.ActionServiceBean;
import com.gf.service.ActionService;

@Stateless(name="Storage")
@Local(ActionService.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class Storage extends ActionServiceBean {


	@Override
	public ActionService createActionService() {
		// TODO Auto-generated method stub
		return null;
	}

}
