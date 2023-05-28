package com.codewithdurgesh.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.codewithdurgesh.blog.entities.Comment;

import lombok.Data;

@Data
public class PostDto {
	
	private String title;
	
	private String content;
	
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<Comment> comments=new HashSet<>();
}
