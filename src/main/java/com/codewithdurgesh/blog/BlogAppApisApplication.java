package com.codewithdurgesh.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.codewithdurgesh.blog.config.AppConstants;
import com.codewithdurgesh.blog.entities.Role;
import com.codewithdurgesh.blog.repositories.RoleRepo;

@SpringBootApplication()
public class BlogAppApisApplication  {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;


	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	// @Override
	// public void run(String... args) throws Exception {
	// 	System.out.println(this.passwordEncoder.encode("xyz"));
		
	// 	try {
	// 		Role role1=new Role();
	// 		role1.setId(AppConstants.NORMAL_USER);
	// 		role1.setName("ROLE_NORMAL");
	// 		Role role2=new Role();
	// 		role2.setId(AppConstants.ADMIN_USER);
	// 		role2.setName("ROLE_ADMIN");
	// 		List<Role> roles = List.of(role1,role2);
	// 		this.roleRepo.saveAll(roles);
			
			
	// 	}
	// 	catch (Exception e) {
	// 		e.printStackTrace();
	// 	}
	// }

}
