package project.Tasklist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import project.Tasklist.domain.Priority;
import project.Tasklist.domain.Status;
import project.Tasklist.domain.Task;
import project.Tasklist.domain.TaskRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {

	@Autowired
	TaskRepository taskRepo;
	
	@Autowired
    private TestEntityManager em;
	
	@Test
	public void findByName() {
		List<Task> tasks = taskRepo.findByName("Exercise 1");
		assertThat(tasks).hasSize(1);
		assertThat(tasks.get(0).getCourse()).isEqualTo("SWD4TN020-3008");
	}
	
	@Test
	public void createNewBook() {
		Task task = new Task("Test", "Course", "Date", new Status("WAITING"), new Priority("MAJOR"));
		taskRepo.save(task);
		assertThat(task.getId()).isNotNull();
	}
	
	@Test
    public void deleteById() {
        Task task = new Task("Test", "Course", "Date", new Status("DONE"), new Priority("UNDEFINED"));
        final Long id = em.persistAndGetId(task, Long.class);
        taskRepo.deleteById(id);
        em.flush();
        Task after = em.find(Task.class, id);
        assertThat(after).isNull();
    }
}
