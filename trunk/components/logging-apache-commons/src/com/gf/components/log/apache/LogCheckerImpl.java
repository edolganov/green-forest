package com.gf.components.log.apache;

import com.gf.log.LogChecker;

public class LogCheckerImpl implements LogChecker {

	@Override
	public boolean isValid() {
		
		try {
			Class.forName("org.apache.commons.logging.LogFactory");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getProviderClass() {
		String packageName = this.getClass().getPackage().getName();
		return packageName+".ApacheLogProvider";
	}

}
