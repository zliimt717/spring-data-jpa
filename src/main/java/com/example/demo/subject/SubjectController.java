package com.example.demo.subject;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import com.example.demo.teacher.Teacher;
import com.example.demo.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Subject> findAllSubject(){
        return subjectRepository.findAll();
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject){
        return subjectRepository.save(subject);
    }

    @PutMapping("/{subjectId}/students/{studentId}")
    public Subject enrollStudentToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long studentId

    ){
        Subject subject=subjectRepository.findById(subjectId).get();
        Student student=studentRepository.findById(studentId).get();
        subject.enrolledStudent(student);

        return subjectRepository.save(subject);
    }

    @PutMapping("/{subjectId}/teacher/{teacherId}")
    public Subject assignTeachertoSubject(
            @PathVariable Long subjectId,
            @PathVariable Long teacherId

    ){
        Subject subject=subjectRepository.findById(subjectId).get();
        Teacher teacher=teacherRepository.findById(teacherId).get();
        subject.setTeacher(teacher);

        return subjectRepository.save(subject);
    }


}
