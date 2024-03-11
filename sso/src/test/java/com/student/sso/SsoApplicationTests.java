package com.student.sso;

import com.student.sso.entity.User;
import com.student.sso.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SsoApplicationTests {
	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
		User user = new User();
		user.setUserPassword("123");
		user.setUserPhone("13108229721");
		user.setUserName("大师");
		userRepository.save(user);
	}

	@Test
	void contextLoads1() {
		User user = userRepository.getByUserNameAndUserPassword("大师", "123");
		System.out.println(user);
	}

	@Test
	void testRest() {

	}
}
