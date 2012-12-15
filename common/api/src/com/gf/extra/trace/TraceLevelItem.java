/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gf.extra.trace;

import java.util.ArrayList;
import java.util.List;

public class TraceLevelItem extends TraceElement {
	
	public final Object owner;
	
	private List<TraceLevel> children;

	public TraceLevelItem(Object owner) {
		super();
		this.owner = owner;
	}
	
	public Object getOwner() {
		return owner;
	}

	@Override
	public List<TraceElement> getChildren(){
		if(children == null){
			return new ArrayList<TraceElement>();
		}
		return new ArrayList<TraceElement>(children);
	}

	@Override
	public void addChild(TraceElement child) {
		if(child instanceof TraceLevel){
			if(children == null){
				children = new ArrayList<TraceLevel>();
			}
			children.add((TraceLevel)child);
		} else {
			throw new IllegalStateException("expected type: "+TraceElement.class+" but was: "+child);
		}

	}


	@Override
	public String toStringCurObject() {
		return getClass().getSimpleName()+" [owner="+owner+", childrenCount="+(children == null? 0 : children.size())+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TraceLevelItem other = (TraceLevelItem) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

}
