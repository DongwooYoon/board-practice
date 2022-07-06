package jpa.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa.springboot.entity.Board;
import jpa.springboot.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	List<Comment> findByBoard(Board board);
}

