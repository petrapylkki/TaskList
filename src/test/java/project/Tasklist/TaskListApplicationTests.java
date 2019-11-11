package project.Tasklist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import project.Tasklist.web.TaskListController;
import project.Tasklist.web.UserController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskListApplicationTests {
	@Autowired
	TaskListController taskcontroller;
	@Autowired
	UserController usercontroller;

	@Test
	public void contextLoadsTasks() throws Exception {
		assertThat(taskcontroller).isNotNull();
	}
	@Test
	public void contextLoadsUsers() throws Exception {
		assertThat(usercontroller).isNotNull();
	}

}
