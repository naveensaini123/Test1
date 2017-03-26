package test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.services.TeacherService;

@RestController
public class TeacherController {

	TeacherService teacherService;
	
	@Autowired
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	@RequestMapping(value="/insert/teacher",method = RequestMethod.POST)
	public String insertTeacher(@RequestParam String name,@RequestParam String subject) {
		return teacherService.insertTeacher(name, subject);
	}
	
	@RequestMapping(value="/update/teacher",method = RequestMethod.POST)
	public String updateTeacher(@RequestParam String currentName,@RequestParam String currentSubject,@RequestParam String newName,@RequestParam String newSubject) {
		return teacherService.updateTeacher(currentName, currentSubject, newName, newSubject);
	}
	
	@RequestMapping(value="/delete/teacher",method = RequestMethod.POST)
	public String deleteTeacher(@RequestParam String name,@RequestParam String subject) {
		return teacherService.deleteTeacher(name, subject);
	}
	
	@RequestMapping(value="/get/teacher",method = RequestMethod.GET)
	public String getTeacher(@RequestParam String name,@RequestParam String subject) {
		return teacherService.getTeacher(name, subject);
	}
	

}
