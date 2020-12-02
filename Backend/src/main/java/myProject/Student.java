package myProject;

import java.util.Set;

import javax.persistence.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column 
	String name;
	
	@Column 
	String password;
	
	@OneToMany(mappedBy = "student")
	Set<StudentCourse> studentCourses;
	
	public Student() {}
	
	public Student(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
}