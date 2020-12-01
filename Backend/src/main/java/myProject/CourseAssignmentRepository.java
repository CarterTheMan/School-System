package myProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseAssignmentRepository extends JpaRepository<CourseAssignment, Integer> {

}