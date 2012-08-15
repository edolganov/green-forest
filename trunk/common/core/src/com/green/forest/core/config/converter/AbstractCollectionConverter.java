package com.green.forest.core.config.converter;

import java.util.Collection;
import java.util.Iterator;

import com.green.forest.util.Util;



@SuppressWarnings("rawtypes")
public abstract class AbstractCollectionConverter<T extends Collection> extends AbstractConverter<T>{
	
	Class<T> collectionType;
	
	public AbstractCollectionConverter(Class<T> collectionType) {
		this.collectionType = collectionType;
	}
	
	@Override
	protected boolean canConvertEmptyString() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T convertToValue(String str) throws Exception {
		if(Util.isEmpty(str)){
			return (T)collectionType.newInstance();
		}
		
		T out = collectionType.newInstance();
		String[] strings = str.split(",");
		for(String val : strings){
			out.add(val);
		}
		return (T)out;
	}

	@Override
	protected String convertToStore(T value) throws Exception {
		if(value.size() == 0){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		Iterator it = value.iterator();
		while(it.hasNext()){
			Object str = it.next();
			sb.append(str);
			if(it.hasNext()){
				sb.append(',');
			}
		}
		return sb.toString();
		
	}



}
