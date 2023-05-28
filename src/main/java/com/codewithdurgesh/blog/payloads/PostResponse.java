package com.codewithdurgesh.blog.payloads;

import java.util.List;

import lombok.Data;


@Data
public class PostResponse {
	
	private List<PostDto> content;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private long totalElements;
	
	private int totalPages;
	
	private boolean islastPage;

}
