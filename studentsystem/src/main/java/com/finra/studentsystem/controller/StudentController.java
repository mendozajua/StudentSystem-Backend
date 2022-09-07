package com.finra.studentsystem.controller;


import com.finra.studentsystem.exceptions.StudentNotFoundException;
import com.finra.studentsystem.model.Student;
import com.finra.studentsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        studentService.saveStudent(student);
        return "New Student has been added!";
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteByStudent(@PathVariable Long id){
        Optional<Student> s = studentService.findById(id);
        if (!s.isPresent()){
            throw new StudentNotFoundException("Student with id:" + id.toString() + " not found!");
        }
        studentService.deleteStudent(id);

    }

    @GetMapping("/get/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id){
        return Optional.ofNullable(studentService.findStudentById(id).orElseThrow(StudentNotFoundException::new));
    }

    @PutMapping("/get/{id}")
    public Optional<Student> updateStudent(@RequestBody Student newStudent, @PathVariable Long id){
        return Optional.ofNullable(studentService.updateStudent(newStudent, id));
    }
}
