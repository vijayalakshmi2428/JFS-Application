package com.JFS.Integration.school.repository;

import com.JFS.Integration.school.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {

    List<School> findByNameContainingIgnoreCase(String name);

    List<School> findByLocation(String location);
}
