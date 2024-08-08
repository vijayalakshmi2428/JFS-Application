package com.JFS.Integration.student.repository;

import com.JFS.Integration.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findBySchoolId(Long schoolId);

    List<Student> findByFirstnameContainingIgnoreCase(String name);
}