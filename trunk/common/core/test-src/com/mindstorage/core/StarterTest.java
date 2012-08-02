package com.mindstorage.core;

import java.util.HashMap;

import org.junit.Test;

import junit.framework.Assert;


public class StarterTest extends Assert {
	
	@Test
	public void all_default(){
		Starter starter = new Starter();
		starter.initApp(null);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void unknow_storage(){
		HashMap<String, String> ops = new HashMap<String, String>();
		ops.put("storage-type", null);
		
		Starter starter = new Starter();
		starter.initApp(ops);
	}
	
	@Test
	public void h2_storage(){
		HashMap<String, String> ops = new HashMap<String, String>();
		ops.put("storage-type", "h2");
		
		Starter starter = new Starter();
		starter.initApp(ops);
	}

}
