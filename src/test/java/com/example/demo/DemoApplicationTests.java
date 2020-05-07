package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		friends f=new friends();
		FriendsRepository friend=new FriendsRepository();
		f.setBirthday("Dec");
		f.setCity("jsr");
		f.setName("Hello");
		f.setId(5);
		
		assertEquals(1, friend.putFriends(f));
	}

}
