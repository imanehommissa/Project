package ma.ac.emi.students.programme.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> getCourseByStudents(Set<Student> students);
}