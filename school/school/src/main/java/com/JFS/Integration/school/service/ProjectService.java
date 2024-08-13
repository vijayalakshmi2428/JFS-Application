package com.JFS.Integration.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JFS.Integration.school.model.Project;
import com.JFS.Integration.school.repository.ProjectRepository;




@Service
public class ProjectService {
	
	@Autowired
    private  ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return  projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project updateProject(Long id, Project project) {
        Optional<Project> projectOptional=projectRepository.findById(id);
        if(projectOptional.isPresent())
        {
            Project existingProject=projectOptional.get();
            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());
            return projectRepository.save(existingProject);
        }else {
            return null;
        }
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
