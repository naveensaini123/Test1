package test.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import test.models.ChildSubject;
import test.models.ParentSubject;

@Repository
public class SubjectDao {

	public ParentSubject getParentSubjectByName(Session session, String subjectName) throws Exception {
		
		Criteria cr = session.createCriteria(ParentSubject.class);
		cr.add(Restrictions.ilike("subjectName", subjectName));
		ParentSubject parentSubject = (ParentSubject) cr.uniqueResult();
		return parentSubject;
	}

	public void insert(Session session, ParentSubject parentSubject) throws SQLException {

		session.save(parentSubject);
	}

	public List<String> getAllSubjectName(Session session) throws SQLException {
		
		Criteria cr = session.createCriteria(ParentSubject.class);
		cr.setProjection(Projections.property("subjectName"));
		List<String> names = (ArrayList<String>) cr.list();
		return names;
	}

	public void insertChild(Session session, ChildSubject childSubject) throws SQLException {
		
		session.save(childSubject);
	}

	public List<ChildSubject> getAllChildSubjectName(Session session) throws SQLException {
	
		Criteria cr = session.createCriteria(ChildSubject.class);
		List<ChildSubject> childSubjects = (ArrayList<ChildSubject>) cr.list();
		return childSubjects;
	}

	public ChildSubject getChildSubjectByChildSubjectandParentSubject(Session session, String childSubjectName,
			String parentSubjectName) throws SQLException {
		
		Criteria cr = session.createCriteria(ChildSubject.class);
		cr.add(Restrictions.ilike("subjectName", parentSubjectName));
		cr.add(Restrictions.ilike("childSubjectName", childSubjectName)); 
		ChildSubject childSubject = (ChildSubject) cr.uniqueResult();
		return childSubject;
	}

	public List<String> getAllChildSubjectNameByParentSubject(Session session, String subjectName) throws SQLException {
		
		Criteria cr = session.createCriteria(ChildSubject.class);
		cr.add(Restrictions.ilike("subjectName", subjectName));
		cr.setProjection(Projections.property("childSubjectName"));
		List<String> names = (ArrayList<String>) cr.list();
		return names;
	}

}
