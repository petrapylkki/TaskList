package project.Tasklist.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.Tasklist.domain.SignUpForm;
import project.Tasklist.domain.User;
import project.Tasklist.domain.UserRepository;

@Controller
public class UserController {
	// wires repositories to controller
	@Autowired 
	UserRepository userRepo;
	
	@RequestMapping(value = "signup")
    public String addStudent(Model model){
    	model.addAttribute("signupform", new SignUpForm());
        return "signup";
    }
	
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	if (userRepo.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		userRepo.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }
	
	//fetch all saved users from user repository
	@GetMapping(value="/users")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String getAllUsers(Model model) {
		List<User> users = (List<User>) userRepo.findAll();
		model.addAttribute("users", users);
		return "users";
	}
	
	//deletes user by id
	@GetMapping(value = "/deleteuser/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteUser(@PathVariable("id") Long userId) {
		userRepo.deleteById(userId);
		return "redirect:../users";
	}
	
	/*
	//edits a task by id
	@GetMapping(value="/edituser/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		model.addAttribute("task", taskRepo.findById(id));
		model.addAttribute("status", statusRepo.findAll());
		model.addAttribute("priority", priorityRepo.findAll());
		return "edit";
	} */
}
