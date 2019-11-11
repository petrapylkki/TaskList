package project.Tasklist.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.Tasklist.domain.PriorityRepository;
import project.Tasklist.domain.Status;
import project.Tasklist.domain.StatusRepository;
import project.Tasklist.domain.Task;
import project.Tasklist.domain.TaskRepository;

@Controller
public class TaskListController {
	// wires automatically repositories to controller
	@Autowired TaskRepository taskRepo;
	@Autowired StatusRepository statusRepo;
	@Autowired PriorityRepository priorityRepo;

	//redirects to index-page by default
	@RequestMapping("/")
	public String frontPage() {
		return "redirect:/tasklist";
	}

	//redirects to login page
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
	//fetch all the saved tasks from the task repository
	@GetMapping(value = "/tasklist")
	public String getTasks(Model model) {
		List<Task> tasks =  (List<Task>) taskRepo.findAll();
		model.addAttribute("tasks", tasks);
		return "index"; 
	}
	
	//fetch all the tasks by given status
	@GetMapping(value="/tasklistbystatus")
	public String displayByStatus(@RequestParam(name="status")Status status, Model model) {
		List<Task> tasks = (List<Task>) taskRepo.findByStatus(status);
		model.addAttribute("tasks", tasks);
		return "index";
	}
	
	//adds a new task to repository
	@GetMapping(value = "/add")
	public String addTask(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("status", statusRepo.findAll());
		model.addAttribute("priority", priorityRepo.findAll());
		return "addtask";
	}
	
	//saves the task filled in addtask.html form
	@PostMapping(value = "/add")
	public String saveNewTask(@ModelAttribute Task task) {
		taskRepo.save(task);
		return "redirect:/tasklist";
	}
	
	//deletes a task by id
	@GetMapping(value = "/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteTask(@PathVariable("id") Long taskId) {
		taskRepo.deleteById(taskId);
		return "redirect:../tasklist";
	}
	
	//edits a task by id
	@GetMapping(value="/edit/{id}")
	public String editTask(@PathVariable("id") Long id, Model model) {
		model.addAttribute("task", taskRepo.findById(id));
		model.addAttribute("status", statusRepo.findAll());
		model.addAttribute("priority", priorityRepo.findAll());
		return "edit";
	}
	
	
}
