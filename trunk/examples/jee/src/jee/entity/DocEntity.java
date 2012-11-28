package jee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


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
	
	

}
