package myProject;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentController {
	@Autowired
	StudentRepository students;
	
	@RequestMapping(method = RequestMethod.GET, path = "/students")
	List<Student> getAllStudents() {
		return students.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/student/{id}")
	Student getStudent(@PathVariable Integer id) {
		return students.findOne(id);
	}
}