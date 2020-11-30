package myProject;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TeacherCourseController {
	@Autowired
	TeacherCourseRepository teacherCourses;
	
	//This allows the user to link a teacher to a course and create a TeacherCourse
	@RequestMapping(method = RequestMethod.POST, path = "/link-teacher-course")
	TeacherCourse createTeacherCourse(@RequestBody TeacherCourse tc) {
		teacherCourses.save(tc);
		return tc;
	}
	
	//This returns a list of all the TeacherCourses
	@RequestMapping(method = RequestMethod.GET, path = "/teacherCourses")
	List<TeacherCourse> getAllTeacherCourses() {
		return teacherCourses.findAll();
	}
	
	//This returns an individual TeacherCourse
	@RequestMapping(method = RequestMethod.GET, path = "/teacherCourse/{id}")
	TeacherCourse getTeacherCourse(@PathVariable Integer id) {
		return teacherCourses.findOne(id);
	}
}