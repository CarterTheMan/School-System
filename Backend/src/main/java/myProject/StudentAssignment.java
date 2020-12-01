package myProject;

import java.util.Set;

import javax.persistence.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Entity
@Table(name = "student_assignment")
public class StudentAssignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    Student student;
	
	@ManyToOne
    @JoinColumn(name = "course_assignment_id", nullable = false)
    CourseAssignment courseAssignment;
	
	@Column 
	Integer grade;
	
	@Column 
	String feedback;
	
	public StudentAssignment() {}
	
	public StudentAssignment(Student s, CourseAssignment ca, Integer grade, String feedback) {
		this.student = s;
		this.courseAssignment = ca;
		this.grade = grade;
		this.feedback = feedback;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public CourseAssignment getCourseAssignment() {
		return courseAssignment;
	}
	
	public Integer getGrade() {
		return grade;
	}
	
	public String getFeeback() {
		return feedback;
	}
	
}