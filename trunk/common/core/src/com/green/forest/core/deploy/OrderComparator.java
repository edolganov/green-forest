package com.green.forest.core.deploy;

import java.util.Comparator;

import com.green.forest.api.annotation.Order;

public class OrderComparator implements Comparator<Object>{

	@Override
	public int compare(Object a, Object b) {
		int aOrder = Order.DEFAULT_VAL;
		int bOrder = Order.DEFAULT_VAL;
		
		//by annotation
		Order aOrderAnn = a.getClass().getAnnotation(Order.class);
		Order bOrderAnn = b.getClass().getAnnotation(Order.class);
		if(aOrderAnn != null){
			aOrder = aOrderAnn.value();
		}
		if(bOrderAnn != null){
			bOrder = bOrderAnn.value();
		}
		
		return Integer.compare(aOrder, bOrder);
	}

}
