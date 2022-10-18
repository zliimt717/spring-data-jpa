package com.example.demo.subject;

import com.example.demo.student.Student;
import com.example.demo.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subject {
    @Id
    @GeneratedValue
    private Long subId;
    @Column(name = "subject_name",nullable = false)
    private String subName;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teachId")
    private Teacher teacher;

    @ManyToMany
    @JoinTable(
            name="student_enrolled",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )

    private Set<Student> enrolledStudent=new HashSet<>();

    public void enrolledStudent(Student student) {
        enrolledStudent.add(student);
    }

    public Long getSubId() {
        return subId;
    }

    public String getSubName() {
        return subName;
    }

    //@JsonManagedReference
    public Set<Student> getEnrolledStudent() {
        return enrolledStudent;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
