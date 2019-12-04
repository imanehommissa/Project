package ma.ac.emi.students.programme.teacher;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

    @Entity
    public class Course {
        @javax.persistence.Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long Id;
        private String intitule;
        @JsonFormat(pattern="yyyy-MM-dd")
        private Date date;
        private String heuredebut;
        private String heurefin;
        @JsonIgnore
        @ManyToOne
        //@JoinColumn(name = "teacher_id",insertable = false, updatable = false)
        private Teacher teacher;

        @ManyToMany
        Set<Student> students;

        public Course() {
            super();
        }

        public Course(String intitule,  Date date, String heuredebut, String heurefin) {
            this.intitule = intitule;
            this.date=date;
            this.heuredebut = heuredebut;
            this.heurefin = heurefin;
        }

        public Long getId() {
            return Id;
        }

        public void setId(Long id) {
            Id = id;
        }

        public String getIntitule() {
            return intitule;
        }

        public void setIntitule(String intitule) {
            this.intitule = intitule;
        }


        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getHeuredebut() {
            return heuredebut;
        }

        public void setHeuredebut(String heuredebut) {
            this.heuredebut = heuredebut;
        }

        public String getHeurefin() {
            return heurefin;
        }

        public void setHeurefin(String heurefin) {
            this.heurefin = heurefin;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        public Set<Student> getStudents() {
            return students;
        }

        public void setStudents(Set<Student> students) {
            this.students = students;
        }
    }

