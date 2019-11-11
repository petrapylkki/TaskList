package project.Tasklist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import project.Tasklist.domain.User;
import project.Tasklist.domain.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTest {
	@Autowired
	private UserRepository urepository;

    @Test
    public void createNewUser() {
    	User testUser = new User("testUser", "1234", "ADMIN");
    	urepository.save(testUser);
    	assertThat(testUser.getId()).isNotNull();
    }    
}
