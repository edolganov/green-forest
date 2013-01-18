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
