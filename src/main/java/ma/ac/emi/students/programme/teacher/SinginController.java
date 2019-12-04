package ma.ac.emi.students.programme.teacher;

import org.springframework.web.bind.annotation.*;

@RestController
public class SinginController {
    User user = new User();

    @PostMapping("/student/singin")
    public String Verify(@RequestBody User u){
        if(user.getName().equals(u.getName())&&user.getPassword().equals(u.getPassword())) return "true";
        else return "false";
    }
}