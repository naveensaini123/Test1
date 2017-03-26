package test.services;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dao.SubjectDao;
import test.models.ChildSubject;
import test.models.ParentSubject;

@Service
public class SubjectService {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	SubjectDao subjectDao;

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

	public String getAllChildSubjectofASubject(String subjectName) {

		Session session = getSession();
		if(session == null) {
			return new String("Unable to open session");
		}
		try {
			ParentSubject parentSubject = subjectDao.getParentSubjectByName(session,subjectName);
			if(parentSubject == null) {
				return new String("No Subject exist with name "+subjectName);
			}
			//List<ChildSubject> childSubjects = parentSubject.getChildSubjects();
			//System.out.println(childSubjects.size());
			List<String> childSubjectNames = subjectDao.getAllChildSubjectNameByParentSubject(session,subjectName); 
			StringBuffer subject = new StringBuffer();
			for(String childSubject : childSubjectNames) { 
				if(subject.length() == 0) {
					subject.append(childSubject);
				}
				else {
					subject.append(","+childSubject);
				}
			}

			return new String("Subject "+subjectName+" contain following subjects "+subject.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return new String("Oops ! something went wrong");
		}
		finally {
			session.close();
		}
	}

	public String addParentSubject(String subjectName) {

		Session session = getSession();
		if(session == null) {
			return new String("Unable to open session");
		}
		try {
			ParentSubject parentSubject = subjectDao.getParentSubjectByName(session, subjectName);
			if(parentSubject != null) {
				return new String ("A subject already exist with this name "+subjectName);
			}
			parentSubject = new ParentSubject();
			parentSubject.setSubjectName(subjectName);
			Transaction tx = session.beginTransaction();
			subjectDao.insert(session,parentSubject);
			tx.commit();
			return new String("Subject "+subjectName+" successfully addedd");
		} catch (Exception e) {
			e.printStackTrace();
			return new String ("Oops ! something went wrong");
		}
		finally {
			session.close();
		}
	}

	public String getAllParentSubject() {

		Session session = getSession();
		if(session == null) {
			return new String("Unble to open session");
		}
		try {
			List<String> subjectNames  = subjectDao.getAllSubjectName(session);
			StringBuffer names = new StringBuffer();
			for(String name : subjectNames) {
				if(names.length() == 0) {
					names.append(name);
				}
				else {
					names.append(","+name); 
				}
			}
			return new String("Subjects are "+names.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return new String("Oops ! something went wrong");
		}
		finally {
			session.close();
		}
	}

	public String addChildSubject(String parentSubjectName, String childSubjectName) {

		Session session = getSession();
		if(session == null) {
			return new String("Unble to open session");
		}
		try {
			ParentSubject parentSubject = subjectDao.getParentSubjectByName(session, parentSubjectName); 
			if(parentSubject == null) {
				return new String ("No parent subject exist with name "+parentSubjectName);
			}
			ChildSubject childSubject = subjectDao.getChildSubjectByChildSubjectandParentSubject(session,childSubjectName,parentSubjectName);
			if(childSubject != null) {
				return new String ("Child subject already exist with same parent subject");
			}
			
			childSubject = new ChildSubject();
			childSubject.setChildSubjectName(childSubjectName);
			childSubject.setSubjectName(parentSubjectName);
			Transaction tx = session.beginTransaction();
			subjectDao.insertChild(session, childSubject);
			tx.commit();
			return new String("Child subject with name "+childSubjectName+" having parent subject name "+parentSubjectName+" successfully addedd");
		} catch (Exception e) {
			e.printStackTrace();
			return new String("Oops ! something went wrong");
		}
		finally {
			session.close();
		}
	}

	public String getAllChildSubject() {

		Session session = getSession();
		if(session == null) {
			return new String("Unble to open session");
		}
		try {
			List<ChildSubject> childSubjects = subjectDao.getAllChildSubjectName(session);
			JSONArray jArray = new JSONArray();
			for(ChildSubject childSubject : childSubjects) {
				jArray.put(new JSONObject(childSubject));
			} 
			return new String("Child Subjects are "+jArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return new String("Oops ! something went wrong");
		}
		finally {
			session.close();
		}
	}
}
