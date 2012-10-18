package servlet_jdbc.common.model;

import java.util.List;

public class Page<T>{
	
	public List<T> list;
	public int pageIndex;
	public int limit;
	
	
	
	public Page(List<T> list, int pageIndex, int limit) {
		super();
		this.list = list;
		this.pageIndex = pageIndex;
		this.limit = limit;
	}
	
	
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	

}
