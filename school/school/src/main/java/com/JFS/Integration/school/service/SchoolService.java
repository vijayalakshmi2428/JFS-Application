package com.JFS.Integration.school.service;

import com.JFS.Integration.school.model.School;
import com.JFS.Integration.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    public List<School> getAllSchools() {
        return  schoolRepository.findAll();
    }

    public Optional<School> getSchoolById(Long id) {
        return schoolRepository.findById(id);
    }

    public School updateSchool(Long id, School school) {
        Optional<School> schoolOptional=schoolRepository.findById(id);
        if(schoolOptional.isPresent())
        {
            School existingschool=schoolOptional.get();
            existingschool.setName(school.getName());
            existingschool.setLocation(school.getLocation());
            return schoolRepository.save(existingschool);
        }else {
            return null;
        }
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }

    public List<School> getSchoolsByLocation(String location) {
        return schoolRepository.findByLocation(location);
    }

    public List<School> searchSchoolsByName(String name) {
        return schoolRepository.findByNameContainingIgnoreCase(name);
    }
}
