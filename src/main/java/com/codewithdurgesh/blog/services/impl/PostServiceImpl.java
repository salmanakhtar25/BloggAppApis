package com.codewithdurgesh.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.repositories.CategoryRepo;
import com.codewithdurgesh.blog.repositories.PostRepo;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer catId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
	    Category category = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("category", "categoryId", catId));
		Post post = this.modelmapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
	    return this.modelmapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post updatedPost = this.postRepo.save(post);
		return this.modelmapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
		return this.modelmapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllpost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {	
	   
		Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():(Sort.by(sortBy).descending());		
		
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> pageposts = this.postRepo.findAll(p);
		List<Post> posts = pageposts.getContent();
		List<PostDto> postDtos = posts.stream().map(post ->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResp=new PostResponse();
		postResp.setContent(postDtos);
		postResp.setPageNumber(pageposts.getNumber());
		postResp.setPageSize(pageposts.getSize());
		postResp.setTotalElements(pageposts.getTotalElements());
		postResp.setTotalPages(pageposts.getTotalPages());
		postResp.setIslastPage(pageposts.isLast());
		return postResp;
	}

	@Override
	public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId", postId));
	    this.postRepo.delete(post);  
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "categoryId", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDtos = posts.stream().map(post->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
	    List<Post> posts = this.postRepo.findByUser(user);
	    List<PostDto> postDtos = posts.stream().map(post->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
	    return postDtos;	    
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postDtos = posts.stream().map(post->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
