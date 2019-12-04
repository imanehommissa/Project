package ma.ac.emi.students.programme.teacher;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import ma.ac.emi.students.programme.teacher.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public CourseController() {
    }

    @GetMapping({"/courses"})
    public List<Course> retrieveAllCourses() {
        return this.courseRepository.findAll();
    }

    @GetMapping({"/courses/{id}"})
    public Course retrieveCourse(@PathVariable long id) {
        Optional<Course> course = this.courseRepository.findById(id);
        if (!course.isPresent()) {
            throw new CourseNotFoundException("id-" + id);
        } else {
            return (Course)course.get();
        }
    }

    @DeleteMapping({"/courses/{id}"})
    public void deleteCourse(@PathVariable long id) {
        this.courseRepository.deleteById(id);
    }

    @PostMapping({"/courses/{id}"})
    public ResponseEntity<Object> createCourse(@RequestBody Course course, @PathVariable long id) {
        Teacher t = (Teacher)this.teacherRepository.findById(id).get();
        Course c = new Course();
        c.setIntitule(course.getIntitule());
        c.setTeacher(t);
        t.getCourses().add(c);
        Course savedCourse = (Course)this.courseRepository.save(c);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(new Object[]{savedCourse.getId()}).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping({"/courses/{id}/{idt}"})
    public ResponseEntity<Object> updateCourse(@RequestBody Course course, @PathVariable long id, @PathVariable long idt) {
        Optional<Course> courseOptional = this.courseRepository.findById(id);
        if (!courseOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Teacher t = (Teacher)this.teacherRepository.findById(idt).get();
            Course c = new Course();
            c.setId(id);
            c.setIntitule(course.getIntitule());
            c.setTeacher(t);
            t.getCourses().add(c);
            this.courseRepository.save(c);
            return ResponseEntity.noContent().build();
        }
    }
}
