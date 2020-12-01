package myProject;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CourseAssignmentController {
	@Autowired
	CourseAssignmentRepository courseAssignment;
	
	//This allows the user to add a CourseAssignment
	@RequestMapping(method = RequestMethod.POST, path = "/add-course-assignment")
	CourseAssignment createCourseAssignment(@RequestBody CourseAssignment ca) {
		courseAssignment.save(ca);
		return ca;
	}
	
	//This returns a list of all the CourseAssignment
	@RequestMapping(method = RequestMethod.GET, path = "/courseAssignments")
	List<CourseAssignment> getAllCourseAssignments() {
		return courseAssignment.findAll();
	}
	
	//This returns an individual CourseAssignment
	@RequestMapping(method = RequestMethod.GET, path = "/courseAssignment/{id}")
	CourseAssignment getCourseAssignment(@PathVariable Integer id) {
		return courseAssignment.findOne(id);
	}
}