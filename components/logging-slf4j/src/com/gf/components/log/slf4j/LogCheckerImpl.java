package com.gf.components.log.slf4j;

import com.gf.log.LogChecker;

public class LogCheckerImpl implements LogChecker {

	@Override
	public boolean isValid() {
		
		try {
			Class.forName("org.slf4j.LoggerFactory");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getProviderClass() {
		String packageName = this.getClass().getPackage().getName();
		return packageName+".Slf4JLogProvider";
	}

}
