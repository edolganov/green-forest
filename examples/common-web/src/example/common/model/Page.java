package example.common.model;

import java.util.List;

public class Page<T>{
	
	public List<T> list;
	public int index;
	public int limit;
	public int count;
	
	
	public Page(List<T> list, int index, int limit, int count) {
		super();
		this.list = list;
		this.index = index;
		this.limit = limit;
		this.count = count;
	}
	
	
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}



	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	

}
