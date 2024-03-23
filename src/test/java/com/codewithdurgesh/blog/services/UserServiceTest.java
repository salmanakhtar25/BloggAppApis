package com.codewithdurgesh.blog.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.repositories.UserRepo;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {
	
	@MockBean
	private UserRepo userRepo;
	
	@MockBean
	private User user;
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper mapper;
	
	@BeforeEach
	public void init() {
		user=User.builder()
		.name("salman").
		about("I am a coder")
		.email("salman@gmail.com")
		.password("salman.123").build();				
	}

	@Test
	public void createUserTest() {	
		 Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);
		 UserDto newuser = userService.createUser(mapper.map(user,UserDto.class));
		 System.out.println(newuser);	 
		 Assertions.assertNotNull(newuser);
		 Assertions.assertEquals("salman",user.getName());
			
	}
}
