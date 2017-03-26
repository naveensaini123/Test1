package test.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dao.TeacherDao;
import test.models.Teacher;
import test.models.TeacherKey;

@Service
public class TeacherService {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	TeacherDao teacherDao;

	public Session getSession() {
		try {
			Session session = sessionFactory.openSession();
			return session;
		} catch (Exception e) {
			//System.out.println("Unable to open session");
			e.printStackTrace();
			return null;
		}
	}

	public String insertTeacher(String name,String subject) {

		Session session = getSession();
		if(session == null) {
			return new String("Unable to open session");
		}
		try {
			Teacher teacher = teacherDao.getTeacherByNameAndSubject(session,name,subject);
			if(teacher != null) {
				return new String("A teacher with same name and subject already exist");
			}
			teacher = new Teacher();
			TeacherKey teacherKey = new TeacherKey();
			teacherKey.setName(name);
			teacherKey.setSubject(subject);
			teacher.setTeacherKey(teacherKey);

			Transaction tx = session.beginTransaction();
			teacherDao.insert(session,teacher);
			tx.commit();
			return new String("Teacher successfully addedd with name "+name+" and subject "+subject); 
		} catch (Exception e) {
			e.printStackTrace();
			return new String("Oops! something went wrong");
		}
		finally {
			session.close();
		}
	}
	
	public String deleteTeacher(String name,String subject) {
		
		Session session = getSession();
		if(session == null) {
			return new String ("Unalble to open session");
		}
		try {
			Teacher teacher = teacherDao.getTeacherByNameAndSubject(session, name, subject);
			if(teacher == null) {
				return new String("No teacher exist with name "+name+" and subject "+subject); 
			}
			
			Transaction tx = session.beginTransaction();
			teacherDao.delete(session,teacher);
			tx.commit();
			return new String("Teacher successfully deleted with name "+name+" and subject "+subject);
		} catch (Exception e) {
			e.printStackTrace();
			return new String("Oops! something went wrong");
		}
		finally {
			session.close();
		}
	}
	
	public String updateTeacher(String currentName,String currentSubject,String newName,String newSubject) {  /*HERE ASSUMING TEACHER NAME AND SUBJECT NEED TO UPDATE AND IF ONLY
		                                                                                                       ONE OF PROPERTY NEED TO UPDATE THEN OTHER WILL REMAIN SAME */
		/*Primary key should never be updated this method shows just updation process*/
		
		Session session = getSession();
		if(session == null) {
			return new String("Unable to open session");
		}
		try {
			Teacher currentTeacher = teacherDao.getTeacherByNameAndSubject(session, currentName, currentSubject);
			if(currentTeacher == null) {
				return new String ("No Teacher exist with current name "+currentName+" and current subject "+currentSubject);
			}
			Teacher teacher = teacherDao.getTeacherByNameAndSubject(session, newName, newSubject);
			if(teacher != null) {
				return new String("A teacher with same new name and new subject already exist");
			}
			
			TeacherKey teacherKey = new TeacherKey();
			teacherKey.setName(newName);
			teacherKey.setSubject(newSubject);
			Teacher updatedTeacher = new Teacher();
			updatedTeacher.setTeacherKey(teacherKey);
			Transaction tx = session.beginTransaction();
			try {
				teacherDao.delete(session, currentTeacher);  /* since we altering primary key we need to delete current teacher
                                                                and create an updated teacher */    
				teacherDao.insert(session,updatedTeacher); 
				tx.commit();
				return new String("Teacher successfully updated with name "+newName+" and subject "+newSubject);
			} catch (Exception e) {
				if(tx != null) 
					tx.rollback();
				e.printStackTrace();
				return new String ("Oops! something went wrong"); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new String("Oops! something went wrong");
		}
		finally {
			session.close();
		}
	}
	
	public String getTeacher(String name,String subject) {    /* Since teacher model contain only two property getTeacher() method
	                                                             return string with name and subject same as arguement. */
		Session session = getSession();
		if(session == null) {
			return new String("Unable to open session");
		}
		try {
			Teacher teacher = teacherDao.getTeacherByNameAndSubject(session, name, subject);
			if(teacher == null) {
				return new String("No Teacher exist with name "+name+" and subject "+subject);
			}
			return new String("Teacher exist with name "+name+" and subject "+subject);
		} catch (Exception e) {
			e.printStackTrace();
			return new String("Oops! something went wrong");   
		}
		finally {
			session.close();
		}
		
	}

}
