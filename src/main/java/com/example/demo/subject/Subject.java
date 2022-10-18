package com.example.demo.subject;

import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

}
