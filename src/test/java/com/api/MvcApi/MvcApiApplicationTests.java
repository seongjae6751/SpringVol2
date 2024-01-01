package com.api.MvcApi;

import com.api.MvcApi.domain.User;
import com.api.MvcApi.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MvcApiApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void create() {
		User user = new User();

		user.setId("test");
		user.setUsername("jpaTest");
		user.setPassword("123");

		User newUser = userRepository.save(user);
	}

}
