package ma.ac.emi.students.programme.teacher;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String Nom;
    private String Prenom;
    private int Matricule;

    @JsonIgnore
    @ManyToMany
    Set<Course> courses;

    public Student() {
        super();
    }

    public Student(String nom, String prenom) {
        Nom = nom;
        Prenom = prenom;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public int getMatricule() {
        return Matricule;
    }

    public void setMatricule(int matricule) {
        Matricule = matricule;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public void addNewCourse(Set<Course> courses, Set<Course> newCourse){
        courses.add((Course) newCourse);
    }


}
