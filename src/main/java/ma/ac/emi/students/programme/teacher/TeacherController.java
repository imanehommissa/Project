package ma.ac.emi.students.programme.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;

    public TeacherController(){ }

    @GetMapping({"/teachers"})
    public List<Teacher> retrieveAllTeacher() {
        return this.teacherRepository.findAll();
    }

    @GetMapping({"/teachers/{id}"})
    public Teacher retrieveTeacher(@PathVariable long id) {
        Optional<Teacher> teacher = this.teacherRepository.findById(id);
        if (!teacher.isPresent()) {
            throw new TeacherNotFoundException("id-" + id);
        } else {
            return (Teacher)teacher.get();
        }
    }

    @DeleteMapping({"/teachers/{id}"})
    public void deleteTeacher(@PathVariable long id) {
        this.teacherRepository.deleteById(id);
    }

    @PostMapping({"/teachers"})
    public ResponseEntity<Object> createTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = (Teacher)this.teacherRepository.save(teacher);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(new Object[]{savedTeacher.getId()}).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping({"/teachers/{id}"})
    public ResponseEntity<Object> updateTeacher(@RequestBody Teacher teacher, @PathVariable long id) {
            Optional<Teacher> teacherOptional = this.teacherRepository.findById(id);
            if (!teacherOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            } else {
            teacher.setId(id);
            this.teacherRepository.save(teacher);
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping({"/teachers/course/{id}"})
    public List<Teacher> getTeachersByCourse(@PathVariable long id) {
        Optional<Course> c = this.courseRepository.findById(id);
        Set<Course> courses = new HashSet();
        courses.add((Course)c.get());
        return this.teacherRepository.getTeacherByCourses(courses);
    }

    //@GetMapping({"/course/teacher/{id}"})
    //public Course getCourseByTeacher(@PathVariable long id) { }


}

