package test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ChildSubject")
public class ChildSubject {

	@Id
	@GeneratedValue
	long id;
	
	String subjectName;
	
	String childSubjectName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChildSubjectName() {
		return childSubjectName;
	}

	public void setChildSubjectName(String childSubjectName) {
		this.childSubjectName = childSubjectName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	
	
	
}
