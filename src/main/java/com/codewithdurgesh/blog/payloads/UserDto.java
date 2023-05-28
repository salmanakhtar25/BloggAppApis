package com.codewithdurgesh.blog.payloads;



import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import lombok.Data;

@Data
public class UserDto {
	
	private int id;
	
	@NotEmpty
	private String name;
	
	@Email(message="email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=4,max=10,message="password must be between 8 to 10 chars")
	private String password;
	
	
	private String about;
	
	private Set<RoleDTO> roles = new HashSet<>();

}
