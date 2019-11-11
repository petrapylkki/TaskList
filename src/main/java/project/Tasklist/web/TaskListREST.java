package project.Tasklist.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.Tasklist.domain.PriorityRepository;
import project.Tasklist.domain.StatusRepository;
import project.Tasklist.domain.Task;
import project.Tasklist.domain.TaskRepository;
import project.Tasklist.domain.User;
import project.Tasklist.domain.UserRepository;

@RestController
public class TaskListREST {
	@Autowired 
	TaskRepository taskRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	StatusRepository statusRepo;
	
	@Autowired
	PriorityRepository prioRepo;
	
	//REST-method which returns all tasks
	@GetMapping(value="getalltasks")
    public @ResponseBody List<Task> getTasks() {
		return (List<Task>)taskRepo.findAll();
    }
	
	//REST-method which returns task by id
	@GetMapping(value="/task/{id}")
	public @ResponseBody Optional<Task> findTaskRest(@PathVariable("id")Long id) {
		return taskRepo.findById(id);
	}
	
	//REST-method which returns users for only authorized users
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value="/allusers")
	public @ResponseBody List<User> findUserRest() {
		return (List<User>) userRepo.findAll();
	}
	
}
