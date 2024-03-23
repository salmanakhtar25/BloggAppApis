package com.codewithdurgesh.blog.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@MockBean
	private UserService userService;
	
	@MockBean
	private User user;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	
	@BeforeEach
	public void init() {
		user=User.builder()
				.name("salman").
				about("I am a coder")
				.email("salman@gmail.com")
				.password("salman.123").build();
	}
	
	
	@Test
	public void createUserTest() throws Exception{
		UserDto dto = mapper.map(user, UserDto.class);
		
		Mockito.when(userService.createUser(Mockito.any())).thenReturn(dto);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/users").
				contentType(MediaType.APPLICATION_JSON).content(convertObjecttoJsonString(user))
				.accept(MediaType.APPLICATION_JSON))
		        .andDo(print()).andExpect(status().isCreated())
		        .andExpect(jsonPath("$.name").exists());
	}


	private String convertObjecttoJsonString(Object user) {
		
		try {
			return new ObjectMapper().writeValueAsString(user);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
