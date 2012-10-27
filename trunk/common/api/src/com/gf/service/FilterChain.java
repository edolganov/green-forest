package com.gf.service;

public interface FilterChain {
	
	void doNext() throws Exception; 

}
