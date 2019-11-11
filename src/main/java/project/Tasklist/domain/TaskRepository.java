package project.Tasklist.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
	
	List<Task> findByStatus(Status status);
	List<Task> findByPriority(Priority priority);
	List<Task> findByName(String name);
}
