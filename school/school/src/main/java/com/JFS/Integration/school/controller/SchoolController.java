package com.JFS.Integration.school.controller;

import com.JFS.Integration.school.model.School;
import com.JFS.Integration.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;
    
    @GetMapping("/test/index")
    public String getString() {
		return "Project Service";
	}

    @PostMapping("/school")
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        School createdSchool = schoolService.createSchool(school);
        return new ResponseEntity<>(createdSchool, HttpStatus.CREATED);
    }

    @GetMapping("/schools")
    public ResponseEntity<List<School>> getAllSchools() {
        List<School> schools = schoolService.getAllSchools();
        return new ResponseEntity<>(schools, HttpStatus.OK);
    }

    @GetMapping("/schools/{id}")
    public ResponseEntity<Optional<School>> getSchoolById(@PathVariable Long id) {
        Optional<School> school = schoolService.getSchoolById(id);
        return new ResponseEntity<>(school, HttpStatus.OK);
    }

    @PutMapping("/schools/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Long id, @RequestBody School school) {
        School updatedSchool = schoolService.updateSchool(id, school);
        return new ResponseEntity<>(updatedSchool, HttpStatus.OK);
    }

    @DeleteMapping("/schools/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/schools/by-location/{location}")
    public List<School> getSchoolsByLocation(@PathVariable String location) {
        return schoolService.getSchoolsByLocation(location);
    }

    @GetMapping("/schools/search")
    public List<School> searchSchoolsByName(@RequestParam String name) {
        return schoolService.searchSchoolsByName(name);
    }
}
