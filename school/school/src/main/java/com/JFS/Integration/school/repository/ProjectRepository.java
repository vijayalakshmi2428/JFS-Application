package com.JFS.Integration.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JFS.Integration.school.model.Project;



@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
