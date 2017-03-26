package test.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ParentSubject")
public class ParentSubject {
	 
	@Id
	String subjectName;
	
	/*@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="subjectName")*/
	@Transient
	List<ChildSubject> childSubjects;

	public String getSubjectName() {
		return subjectName;
	}
  
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List<ChildSubject> getChildSubjects() {
		return childSubjects;
	}

	public void setChildSubjects(List<ChildSubject> childSubjects) {
		this.childSubjects = childSubjects;
	}

	
	
	
}
