package ma.ac.emi.students.programme.teacher;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class StudentController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> retrieveAllStudents(){
        return studentRepository.findAll();

    }
    @GetMapping("/students/{id}")
    public Student retrieveStudent(@PathVariable long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent())
            throw new StudentNotFoundException("id-" + id);

        return student.get();
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentRepository.deleteById(id);
    }
    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        student.setId(id);

        studentRepository.save(student);

        return ResponseEntity.noContent().build();
    }
//Add course to student
    @PutMapping({"/student/{id}/{idc}"})
    public ResponseEntity<Object> updateCourse(@RequestBody Course course, @PathVariable long id, @PathVariable long idc) {
            Student s = (Student)this.studentRepository.findById(id).get();
            Course c = new Course();
            c.setId(idc);
            c.setIntitule(course.getIntitule());
            s.getCourses().add(c);
            this.studentRepository.save(s);
            return ResponseEntity.noContent().build();
    }
    @GetMapping("/students/{id}/courses")
    public Course retrieveCousesByStudent(@RequestBody Student student, @PathVariable long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        return (Course) student.getCourses();

    }
    //show me my courses
    @GetMapping("/courses/student/{id}")
    public List< Course> getCourseByStudents(@PathVariable long id){
        Optional<Student> s=studentRepository.findById(id);
        Set<Student> students=new HashSet<>();
        students.add(s.get());

        return courseRepository.getCourseByStudents(students);

    }
    @PutMapping("courses/student/{id}/{idc}")
    public void AddNewCourse(@PathVariable long id, @PathVariable long idc){

    }
}
