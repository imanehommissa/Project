package ma.ac.emi.students.programme.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> getTeacherByCourses(Set<Course> courses);
}