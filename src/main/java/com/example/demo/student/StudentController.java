package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable ("studentId") Long studentId,
                              @RequestParam(required = false) String name){

         Student student=studentRepository.findById(studentId)
                 .orElseThrow(()->new IllegalStateException("Student with id"+studentId+"does not exist"));

         if(name !=null && name.length()>0 && !Objects.equals(student.getStudentName(),name)){
             student.setStudentName(name);
         }
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        boolean existId=studentRepository.existsById(studentId);

        if(!existId){
            throw new IllegalStateException("Student with id"+studentId+"does not exist!");
        }

        studentRepository.deleteById(studentId);
    }

}
