package myProject;

import java.util.Set;

import javax.persistence.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Entity
@Table(name = "course_assignment")
public class CourseAssignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column 
	String title;
	
	@Column 
	String description;
	
	@ManyToOne
    @JoinColumn(name = "teacher_course_id", nullable = false)
    TeacherCourse teacherCourse;
	
	public CourseAssignment() {}
	
	public CourseAssignment(TeacherCourse tc, String title, String description) {
		this.teacherCourse = tc;
		this.title = title;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public TeacherCourse getTeacherCourse() {
		return teacherCourse;
	}
	
}