package project.Tasklist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import project.Tasklist.domain.Priority;
import project.Tasklist.domain.PriorityRepository;
import project.Tasklist.domain.Status;
import project.Tasklist.domain.StatusRepository;
import project.Tasklist.domain.Task;
import project.Tasklist.domain.TaskRepository;
import project.Tasklist.domain.User;
import project.Tasklist.domain.UserRepository;

@SpringBootApplication
public class TaskListApplication {

	private static final Logger log = LoggerFactory.getLogger(TaskListApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(TaskListApplication.class, args);
	}

	@Bean
	public CommandLineRunner taskDemo(TaskRepository taskRepo, StatusRepository statusRepo, PriorityRepository prioRepo, UserRepository userRepo) { 
		return (args) -> {
			log.info("test tasks");
			statusRepo.save(new Status("NEW"));
			statusRepo.save(new Status("IN PROGRESS"));
			statusRepo.save(new Status("WAITING"));
			statusRepo.save(new Status("DONE"));
			
			prioRepo.save(new Priority("Major"));
			prioRepo.save(new Priority("Medium"));
			prioRepo.save(new Priority("Minor"));
			prioRepo.save(new Priority("Undefined"));
			
			taskRepo.save(new Task("Exercise 1", "SWD4TN020-3008", "2019-11-10", statusRepo.findByName("DONE").get(0), prioRepo.findByName("Major").get(0)));
			taskRepo.save(new Task("Exercise 2", "DIG4TN022-3002", "2019-11-2", statusRepo.findByName("IN PROGRESS").get(0), prioRepo.findByName("Medium").get(0)));
			taskRepo.save(new Task("Exercise 3", "BUS1TN012-3006", "2019-10-26", statusRepo.findByName("WAITING").get(0), prioRepo.findByName("Minor").get(0)));
			taskRepo.save(new Task("Exercise 4", "ICT4TN021-3009", "2019-12-5", statusRepo.findByName("NEW").get(0), prioRepo.findByName("Undefined").get(0)));
			taskRepo.save(new Task("Exercise 5", "COM1TN010B-3002", "2019-11-18", statusRepo.findByName("DONE").get(0), prioRepo.findByName("Major").get(0)));

			
			
			// Create users: admin/admin user/user
			User user1 = new User("Petra", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("Admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			User user3 = new User("TestUser", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			userRepo.save(user1);
			userRepo.save(user2);
			userRepo.save(user3);
			
			/* Prints all tasks to console
			log.info("fetch all tasks");
			for (Task task : taskRepo.findAll()) {
				log.info(task.toString());
			} */

		};
	}
}
