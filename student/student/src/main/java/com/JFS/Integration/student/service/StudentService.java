package com.JFS.Integration.student.service;

import com.JFS.Integration.student.dto.SchoolDTO;
import com.JFS.Integration.student.model.Student;
import com.JFS.Integration.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String SCHOOL_SERVICE_URL = "http://localhost:8083/api/schools/";

    public Student enrollStudent(Student student) {
        Long schoolId = student.getSchoolId();
        try {
            SchoolDTO school = restTemplate.getForObject(SCHOOL_SERVICE_URL+schoolId,SchoolDTO.class);

            if(school != null)
                return studentRepository.save(student);
            else
                throw new IllegalArgumentException("School with ID "+schoolId+" not found.");

        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("School with ID "+schoolId+" not found.");

        }

    }
    
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent())
            return student.get();
        else
            return null;

    }

    public Student updateStudent(Long id, Student student) {
        Long schoolId = student.getSchoolId();
        try {
            SchoolDTO school = restTemplate.getForObject(SCHOOL_SERVICE_URL+schoolId,SchoolDTO.class);

            if(school != null) {

                student.setId(id);
                return studentRepository.save(student);
            } else {
                throw new IllegalArgumentException("School with ID "+schoolId+" not found.");
            }

        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("School with ID "+schoolId+" not found.");

        }

    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByFirstnameContainingIgnoreCase(name);
    }

    public List<Student> getStudentsBySchool(Long schoolId) {
        try {
            SchoolDTO school = restTemplate.getForObject(SCHOOL_SERVICE_URL+schoolId,SchoolDTO.class);

            if(school != null)
                return studentRepository.findBySchoolId(school.getId());
            else
                throw new IllegalArgumentException("School with ID "+schoolId+" not found.");
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("School with ID "+schoolId+" not found.");
        }
    }
}

