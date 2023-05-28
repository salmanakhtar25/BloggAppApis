package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	CommentDto updateComment(CommentDto commentDto,Integer commentId);
	
	void deleteComment(Integer commentId);
	
	CommentDto getCommentById(Integer commentId);
	
	List<CommentDto> getAllComments();
	
	List<CommentDto> getAllCommentsofPost(Integer PostId);
}
