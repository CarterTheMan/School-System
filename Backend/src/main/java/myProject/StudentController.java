package myProject;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentController {
	@Autowired
	StudentRepository students;
	
	//This allows the user to add a student
	@RequestMapping(method = RequestMethod.POST, path = "/register-student")
	Student createStudent(@RequestBody Student s) {
		students.save(s);
		return s;
	}
	
	//This allows the user to enter a student as input and if it works then it returns the students id number
	@RequestMapping(method = RequestMethod.POST, path = "/login-student")
	String getStudent(@RequestBody Student s)	{
		int j = (int) students.count(); // count() method does not include the number of deleted entities (this causes issues when iterating over id with deleted entity)
		for (int i = 1; i <= (int) j; i++) {
			if (!students.exists(i)) j++; // deleted entity id found. Increment number of iterations to make up for this.
			else	{
				if (s.getName().equals((students.getOne(i)).getName()))	{
					if (s.getPassword().equals(students.getOne(i).getPassword()))	{
						return "" + students.getOne(i).getId();
					}
					else return "Incorrect password";
				}
			}
		}
		return "There are no students with the name " + s.getName();
	}
	
	//This returns a list of all the students
	@RequestMapping(method = RequestMethod.GET, path = "/students")
	List<Student> getAllStudents() {
		return students.findAll();
	}
	
	//This returns an individual student
	@RequestMapping(method = RequestMethod.GET, path = "/student/{id}")
	Student getStudent(@PathVariable Integer id) {
		return students.findOne(id);
	}
	
	@Autowired
	StudentCourseRepository studentCourses;
	
	//Gets all courses of an individual student
	@RequestMapping(method = RequestMethod.GET, path = "/student/{id}/courses")
	List<StudentCourse> getStudentsStudentCourses(@PathVariable Integer id) {
		List<StudentCourse> returned = new ArrayList<StudentCourse>();
		List<StudentCourse> list = studentCourses.findAll();
		for (StudentCourse stu : list) {
			if (stu.student.id == id) {
				returned.add(stu);
			}
		}
		return returned;
	}
	
	//Gets a specific course of an individual student
	@RequestMapping(method = RequestMethod.GET, path = "/student/{id}/course/{sid}")
	StudentCourse getStudentsStudentCouse(@PathVariable Integer id, @PathVariable Integer sid) {
		List<StudentCourse> list = studentCourses.findAll();
		for (StudentCourse stu : list) {
			if (stu.student.id == id && stu.id == sid) {
				return stu;
			}
		}
		return null;
	}
	
	@Autowired
	StudentAssignmentRepository studentAssignments;
	
	//Gets all assignments for a student
	@RequestMapping(method = RequestMethod.GET, path = "/student/{id}/assignments")
	List<StudentAssignment> getStudentAssignments(@PathVariable Integer id) {
		List<StudentAssignment> returned = new ArrayList<StudentAssignment>();
		List<StudentAssignment> list = studentAssignments.findAll();
		for (StudentAssignment stu : list) {
			if (stu.studentCourse.student.id == id) {
				returned.add(stu);
			}
		}
		return returned;
	}
	
	//Gets all assignments for a student
	@RequestMapping(method = RequestMethod.GET, path = "/student/{id}/assignment/{aid}")
	StudentAssignment getStudentAssignment(@PathVariable Integer id, @PathVariable Integer aid) {
		List<StudentAssignment> list = studentAssignments.findAll();
		for (StudentAssignment stu : list) {
			if (stu.studentCourse.student.id == id && stu.id == aid) {
				return stu;
			}
		}
		return null;
	}
	
	//Gets all assignments for an individual course
	@RequestMapping(method = RequestMethod.GET, path = "/student/{id}/course/{sid}/assignments")
	List<StudentAssignment> getStudentCourseAssignments(@PathVariable Integer id, @PathVariable Integer sid) {
		List<StudentAssignment> returned = new ArrayList<StudentAssignment>();
		List<StudentAssignment> list = studentAssignments.findAll();
		for (StudentAssignment stu : list) {
			if (stu.studentCourse.student.id == id && stu.studentCourse.id == sid) {
				returned.add(stu);
			}
		}
		return returned;
	}
	 
	//Gets an individual assignment for an individual course
	@RequestMapping(method = RequestMethod.GET, path = "/student/{id}/course/{sid}/assignment/{aid}")
	StudentAssignment getStudentCourseAssignment(@PathVariable Integer id, @PathVariable Integer sid, @PathVariable Integer aid) {
		List<StudentAssignment> list = studentAssignments.findAll();
		for (StudentAssignment stu : list) {
			if (stu.studentCourse.student.id == id && stu.studentCourse.id == sid && stu.id == aid) {
				return stu;
			}
		}
		return null;
	}
	
	
}