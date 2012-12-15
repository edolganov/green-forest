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
package jee.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import example.common.model.Doc;


@NamedQueries({
	@NamedQuery(name=DocEntity.Q_COUNT, query="select count(d) from DocEntity d"),
	@NamedQuery(name=DocEntity.Q_GET_PAGE, query="select d from DocEntity d")
})
@Entity
@Table(name="doc")
public class DocEntity {
	
	public static final String Q_COUNT = "doc.count";
	public static final String Q_GET_PAGE = "doc.page";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable=false, length=100)
	private String name;
	
	

	public DocEntity(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public DocEntity() {
		super();
	}

	public DocEntity(Doc doc) {
		this(doc.id, doc.name);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Doc getDoc(){
		return new Doc(id, name);
	}
	
	public static List<Doc> getDocs(List<DocEntity> list){
		ArrayList<Doc> out = new ArrayList<Doc>();
		for(DocEntity entity : list){
			out.add(entity.getDoc());
		}
		return out;
	}

}
