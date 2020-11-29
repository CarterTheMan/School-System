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
}