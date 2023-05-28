package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer catId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	PostDto getPostById(Integer postId);
	
	PostResponse getAllpost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	void deletePost(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer CategoryId);
	
	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);

}
