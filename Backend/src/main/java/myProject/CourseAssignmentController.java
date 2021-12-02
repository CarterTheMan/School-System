package myProject;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CourseAssignmentController {
	@Autowired
	CourseAssignmentRepository courseAssignment;
	
	@Autowired 
	TeacherCourseRepository teacherCourse;
	
	@Autowired
	StudentCourseRepository studentCourse;
	
	@Autowired 
	StudentAssignmentRepository studentAssignment;
	
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
	
	
	//SHOULD BE EITHER A POST OR A GET
	//This creates a new assignment that trickles down to students
	@RequestMapping(method = RequestMethod.POST, path = "/create-course-assignment/{tcid}/{title}/{description}")
	CourseAssignment createCourseAssignmentTeacher(@RequestBody Integer tcid, @RequestBody String title, @RequestBody String description) {
		//Create the new course assignment
		TeacherCourse temp = teacherCourse.findOne(tcid);
		CourseAssignment created = new CourseAssignment(temp, title, description);
		courseAssignment.save(created);
		
		//Find all student courses for this class saved in students
		List<StudentCourse> allSA = studentCourse.findAll();
		List<StudentCourse> students = new ArrayList<StudentCourse>();
		for (StudentCourse s : allSA) {
			if (s.teacherCourse == temp) {
				students.add(s);
			}
		}
		
		//Create new student assignments
		for (int i = 0; i < students.size(); i++) {
			StudentAssignment sa = new StudentAssignment(students.get(i), created, null, null);
			studentAssignment.save(sa);
		}
		
		return created;
	}
}