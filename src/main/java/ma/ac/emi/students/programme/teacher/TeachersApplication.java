package ma.ac.emi.students.programme.teacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        TeachersApplication.class,
        Jsr310JpaConverters.class
})
public class TeachersApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeachersApplication.class, args);
    }

}
