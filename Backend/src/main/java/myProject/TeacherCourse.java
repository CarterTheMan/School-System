package myProject;

import java.util.Set;

import javax.persistence.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Entity
@Table(name = "teacher_course")
public class TeacherCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    Teacher teacher;
	
	@ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    Course course;
	
	@OneToMany(mappedBy = "teacherCourse")
	Set<StudentCourse> studentCourses;
	
	public TeacherCourse() {}
	
	public TeacherCourse(Teacher t, Course c) {
		this.teacher = t;
		this.course = c;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setTeacher(Teacher t) {
		teacher = t;
	}
	
	public void setCourse(Course c) {
		course = c;
	}
	
}