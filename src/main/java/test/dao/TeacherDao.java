package test.dao;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import test.models.Teacher;
import test.models.TeacherKey;

@Repository
public class TeacherDao {

	public Teacher getTeacherByNameAndSubject(Session session, String name, String subject) throws SQLException {
		
		Criteria cr = session.createCriteria(Teacher.class);
		TeacherKey teacherKey = new TeacherKey();
		teacherKey.setName(name);
		teacherKey.setSubject(subject);
		//cr.add(Restrictions.ilike("teacherKey", teacherKey));  
		cr.add(Restrictions.ilike("teacherKey.subject", subject));
		cr.add(Restrictions.ilike("teacherKey.name", name));
		Teacher teacher = (Teacher) cr.uniqueResult();
		return teacher;
	}

	public void insert(Session session, Teacher teacher) throws SQLException {
		session.save(teacher);
	}

	public void delete(Session session, Teacher teacher) throws SQLException {
		
		session.delete(teacher);  
	}

	public void updateTeacher(Session session, Teacher currentTeacher) throws SQLException {
		
		session.merge(currentTeacher);
	}

	
}
