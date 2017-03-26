package test.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Teacher")
public class Teacher {

	@EmbeddedId
	private TeacherKey teacherKey;

	public TeacherKey getTeacherKey() {
		return teacherKey; 
	}

	public void setTeacherKey(TeacherKey teacherKey) {
		this.teacherKey = teacherKey;
	}
	
	
}
