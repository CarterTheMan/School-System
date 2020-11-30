package myProject;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TeacherController {
	@Autowired
	TeacherRepository teachers;
	
	//This allows the user to add a student
	@RequestMapping(method = RequestMethod.POST, path = "/register-teacher")
	Teacher createTeacher(@RequestBody Teacher t) {
		teachers.save(t);
		return t;
	}
	
	//This allows the user to enter a student as input and if it works then it returns the students id number
	@RequestMapping(method = RequestMethod.POST, path = "/login-teacher")
	String getTeacher(@RequestBody Teacher t)	{
		int j = (int) teachers.count(); // count() method does not include the number of deleted entities (this causes issues when iterating over id with deleted entity)
		for (int i = 1; i <= (int) j; i++) {
			if (!teachers.exists(i)) j++; // deleted entity id found. Increment number of iterations to make up for this.
			else	{
				if (t.getName().equals((teachers.getOne(i)).getName()))	{
					if (t.getPassword().equals(teachers.getOne(i).getPassword()))	{
						return "" + teachers.getOne(i).getId();
					}
					else return "Incorrect password";
				}
			}
		}
		return "There are no teachers with the name " + t.getName();
	}
	
	//This returns a list of all the students
	@RequestMapping(method = RequestMethod.GET, path = "/teachers")
	List<Teacher> getAllTeachers() {
		return teachers.findAll();
	}
	
	//This returns an individual student
	@RequestMapping(method = RequestMethod.GET, path = "/teacher/{id}")
	Teacher getTeacher(@PathVariable Integer id) {
		return teachers.findOne(id);
	}
}