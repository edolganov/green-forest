package com.gf.core.deploy;

import java.util.Comparator;

import com.gf.annotation.Order;

public class OrderComparator implements Comparator<Object>{

	@Override
	public int compare(Object a, Object b) {
		int aOrder = Order.DEFAULT_ORDER;
		int bOrder = Order.DEFAULT_ORDER;
		
		//by annotation
		Order aOrderAnn = a.getClass().getAnnotation(Order.class);
		Order bOrderAnn = b.getClass().getAnnotation(Order.class);
		if(aOrderAnn != null){
			aOrder = aOrderAnn.value();
		}
		if(bOrderAnn != null){
			bOrder = bOrderAnn.value();
		}
		
		return (aOrder<bOrder ? -1 : (aOrder==bOrder ? 0 : 1));
	}

}
