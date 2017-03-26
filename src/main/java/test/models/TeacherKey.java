package test.models;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class TeacherKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Column(name="name")
	String name;
	
	String subject;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
