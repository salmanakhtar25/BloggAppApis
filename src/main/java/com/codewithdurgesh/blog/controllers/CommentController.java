package com.codewithdurgesh.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.CommentDto;
import com.codewithdurgesh.blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		CommentDto newComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(newComment,HttpStatus.CREATED);
	}
	
	@PutMapping("/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,@PathVariable Integer commentId){
		CommentDto updatedComment = this.commentService.updateComment(commentDto, commentId);
	    return new ResponseEntity<CommentDto>(updatedComment,HttpStatus.OK);
	}
	
	@DeleteMapping("/{commentId}")
	public ApiResponse deleteComment(@PathVariable Integer commentId) {
		this.commentService.deleteComment(commentId);
		return new ApiResponse("comment deleted successfully", true);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CommentDto>> getallComments(){
		List<CommentDto> comments = this.commentService.getAllComments();
		return new ResponseEntity<List<CommentDto>>(comments,HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<List<CommentDto>> getAllcommentsOfPost(@PathVariable Integer postId){
		List<CommentDto> comments = this.commentService.getAllCommentsofPost(postId);
		return new ResponseEntity<List<CommentDto>>(comments,HttpStatus.OK);
	}

}
