package com.finra.studentsystem.service;

import com.finra.studentsystem.exceptions.StudentNotFoundException;
import com.finra.studentsystem.model.Student;
import com.finra.studentsystem.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;


    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }


    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }


    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(studentRepo.findById(id).orElseThrow(StudentNotFoundException::new));
    }


    public Optional<Student> findStudentById(Long id) {
        return studentRepo.findById(id);
    }

    public String deleteStudent(Long id) {
        studentRepo.deleteById(id);
        return "Student with id:" + id.toString() + " was deleted";
    }

    public Student updateStudent(Student student,Long id){
        Optional<Student> currentStudent = Optional.ofNullable(studentRepo.findById(id).orElse(null));
        currentStudent.ifPresent(student1 -> {
            student1.setAddress(student.getAddress());
            student1.setEmail(student.getEmail());
            student1.setUsername(student.getUsername());
            student1.setName(student.getName());
        });
        return studentRepo.save(currentStudent.get());
    }
}
