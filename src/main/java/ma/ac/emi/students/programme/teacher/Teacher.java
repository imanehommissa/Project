package ma.ac.emi.students.programme.teacher;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String Nom;
    private String Prenom;
    @OneToMany(mappedBy = "teacher")
    Set<Course> courses;

    public Teacher() { super(); }

    public Teacher(String nom) { Nom = nom; }

    public String getPrenom() { return Prenom; }
    public void setPrenom(String prenom) { Prenom = prenom; }

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


    @Override
    public String toString() {
        return "Teacher{" +
                "Id=" + Id +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", courses=" + courses +
                '}';
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}