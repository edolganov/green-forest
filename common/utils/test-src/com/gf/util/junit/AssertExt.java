package com.gf.util.junit;

import junit.framework.Assert;

public class AssertExt extends Assert {
	
	public static void fail_TODO(){
		fail("TODO");
	}
	
	public static void fail_exception_expected(){
		fail("Exception expected");
	}

}
