package com.JFS.Integration.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JFS.Integration.student.model.Task;



@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	List<Task> findByProjectId(Long projectId);
}
