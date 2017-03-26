package test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.services.SubjectService;

@RestController
public class SubjectController {

	SubjectService subjectservice;
	
	@Autowired
	public SubjectController(SubjectService subjectService) {
		this.subjectservice = subjectService;
	}
	
	@RequestMapping(value="/add/parent/subject",method = RequestMethod.POST)
	public String addParentSubject(@RequestParam String subjectName) {
		return subjectservice.addParentSubject(subjectName);
	}
	
	@RequestMapping(value="/get/all/parent/subject",method = RequestMethod.GET)
	public String getAllParentSubject() {
		return subjectservice.getAllParentSubject();
	}
	
	@RequestMapping(value="/add/child/subject",method = RequestMethod.POST)
	public String addChildSubject(@RequestParam String parentSubjectName,@RequestParam String childSubjectName) {
		return subjectservice.addChildSubject(parentSubjectName,childSubjectName); 
	}
	
	@RequestMapping(value="/get/all/child/of/a/subject",method = RequestMethod.GET)
	public String getAllchild(@RequestParam String subjectName) {
		return subjectservice.getAllChildSubjectofASubject(subjectName);
	}
	
	@RequestMapping(value="/get/all/childsubject",method = RequestMethod.GET)
	public String getAllChildSubject() {
		return subjectservice.getAllChildSubject();
	}


}
