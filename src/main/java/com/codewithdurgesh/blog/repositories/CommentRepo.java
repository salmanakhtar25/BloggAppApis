package com.codewithdurgesh.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codewithdurgesh.blog.entities.Comment;
import com.codewithdurgesh.blog.entities.Post;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

	List<Comment> findByPost(Post post);
}
