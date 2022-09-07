package com.finra.studentsystem.repository;

import com.finra.studentsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//primary key(in our case student id which is int) must match second parameter for jpa interface
@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
}
