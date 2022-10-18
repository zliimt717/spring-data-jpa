package com.example.demo.teacher;

import com.example.demo.subject.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private Long teachId;
    @Column(name = "teacher_name",nullable = false)
    private String teachName;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private Set<Subject> subjects= new HashSet<>();


}
