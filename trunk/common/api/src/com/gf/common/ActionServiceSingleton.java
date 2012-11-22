package com.gf.common;

import com.gf.service.ActionService;

public class ActionServiceSingleton {
	
	private int clientsCount;
	private ActionService service;
	
	public final synchronized ActionService getServiceAndRegisterClient(ActionServiceFactory factory){
		if(service == null){
			clientsCount = 0;
			service = factory.createActionService();
		}
		clientsCount++;
		return service;
	}
	
	public final synchronized void unregisterClient(){
		clientsCount--;
		if(clientsCount < 1){
			service = null;
		}
	}

	public final synchronized int getClientsCount() {
		return clientsCount;
	}

	@Override
	public String toString() {
		return "ActionServiceSingleton [service=" + service + ", clientsCount="
				+ clientsCount + "]";
	}
	
	

}
