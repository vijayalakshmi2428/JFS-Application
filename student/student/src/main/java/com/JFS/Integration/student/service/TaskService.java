package com.JFS.Integration.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.JFS.Integration.student.dto.ProjectDTO;
import com.JFS.Integration.student.model.Task;
import com.JFS.Integration.student.repository.TaskRepository;



@Service
public class TaskService {
	@Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String PROJECT_SERVICE_URL = "http://localhost:8086/api/projects/";

    public Task enrollTask(Task task) {
        Long projectId = task.getProjectId();
        try {
            ProjectDTO project = restTemplate.getForObject(PROJECT_SERVICE_URL+projectId,ProjectDTO.class);

            if(project != null)
                return taskRepository.save(task);
            else
                throw new IllegalArgumentException("Project with ID "+projectId+" not found.");

        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("School with ID "+projectId+" not found.");

        }

    }
    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent())
            return task.get();
        else
            return null;

    }

    public Task updateTask(Long id, Task task) {
        Long projectId = task.getProjectId();
        try {
            ProjectDTO project = restTemplate.getForObject(PROJECT_SERVICE_URL+projectId,ProjectDTO.class);

            if( project != null) {

                 task.setId(id);
                return taskRepository.save(task);
            } else {
                throw new IllegalArgumentException("Project with ID "+projectId+" not found.");
            }

        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Project with ID "+projectId+" not found.");

        }

    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByProject(Long projectId) {
        try {
            ProjectDTO project = restTemplate.getForObject(PROJECT_SERVICE_URL+projectId,ProjectDTO.class);

            if(project != null)
                return taskRepository.findByProjectId(project.getId());
            else
                throw new IllegalArgumentException("project with ID "+projectId+" not found.");
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("project with ID "+projectId+" not found.");
        }
    }
}
