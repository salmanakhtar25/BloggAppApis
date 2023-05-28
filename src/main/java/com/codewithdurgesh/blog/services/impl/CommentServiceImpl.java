package com.codewithdurgesh.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codewithdurgesh.blog.entities.Comment;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.CommentDto;
import com.codewithdurgesh.blog.repositories.CommentRepo;
import com.codewithdurgesh.blog.repositories.PostRepo;
import com.codewithdurgesh.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepo postRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto ,Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId",postId));		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment newComment = this.commentRepo.save(comment);
		return this.modelMapper.map(newComment, CommentDto.class);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "commentId",commentId));
	    comment.setContent(commentDto.getContent());
	    Comment updatedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(updatedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "commentId",commentId));
        this.commentRepo.delete(comment);
	}

	@Override
	public CommentDto getCommentById(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "commentId",commentId));		
		return this.modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public List<CommentDto> getAllComments() {
		List<Comment> comments = this.commentRepo.findAll();
		List<CommentDto> commentDtos = comments.stream().map(comment->modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		return commentDtos;
	}

	@Override
	public List<CommentDto> getAllCommentsofPost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId",postId));		
		List<Comment> comments = this.commentRepo.findByPost(post);
		List<CommentDto> commentDtos = comments.stream().map(comment->modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		return commentDtos;
	}

}
