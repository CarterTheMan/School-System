package myProject;

import javax.persistence.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Entity
@Table(name = "student_course_registration")
public class StudentCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    Student student;
	
	@ManyToOne
    @JoinColumn(name = "teacher_course_id", nullable = false)
    TeacherCourse teacherCourse;
	
	public StudentCourse() {}
	
	public StudentCourse(Student s, TeacherCourse tc) {
		this.student = s;
		this.teacherCourse = tc;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public TeacherCourse getTeacherCourse() {
		return teacherCourse;
	}
	
	public void setStudent(Student s) {
		student = s;
	}
	
	public void setTeacherCourse(TeacherCourse tc) {
		teacherCourse = tc;
	}
	
}