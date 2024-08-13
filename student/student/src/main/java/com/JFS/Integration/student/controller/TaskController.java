package com.JFS.Integration.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JFS.Integration.student.model.Task;
import com.JFS.Integration.student.service.TaskService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
	   @Autowired
	    private TaskService taskService;
	 
	   @GetMapping("/test")
	    public String getString() {
			return "Task Service";
		}

	    @PostMapping("/task")
	    public ResponseEntity<Task> enrollStudent(@RequestBody Task task) {
	        Task enrolledTask = taskService.enrollTask(task);
	        return new ResponseEntity<>(enrolledTask, HttpStatus.CREATED);
	    }

	    @GetMapping("/tasks")
	    public ResponseEntity<List<Task>> getAllTasks() {
	        List<Task> tasks = taskService.getAllTasks();
	        return new ResponseEntity<>(tasks, HttpStatus.OK);
	    }

	    @GetMapping("/tasks/{id}")
	    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
	        Task task = taskService.getTaskById(id);
	        return new ResponseEntity<>(task, HttpStatus.OK);
	    }

	    @PutMapping("/tasks/{id}")
	    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
	        Task updatedTask = taskService.updateTask(id, task);
	        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
	    }

	    @DeleteMapping("/tasks/{id}")
	    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
	        taskService.deleteTask(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    @GetMapping("/by-project/{projectId}")
	    public List<Task> getStudentsBySchool(@PathVariable Long schoolId) {
	        return taskService.getTasksByProject(schoolId);
	    }
}
