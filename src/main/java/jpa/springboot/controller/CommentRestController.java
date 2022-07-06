package jpa.springboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jpa.springboot.entity.Board;
import jpa.springboot.entity.Comment;
import jpa.springboot.entity.Member;
import jpa.springboot.repository.BoardRepository;
import jpa.springboot.repository.CommentRepository;
import jpa.springboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentRestController {
	
	private final MemberRepository memberRepository;
	private final BoardRepository boardRepository;
	private final CommentRepository commentRepository;

// 게시물 전체 조회
	@GetMapping("/api/Comment/{id}")
	public List<Comment> getCommentList(@PathVariable String id) {
		Board board = boardRepository.findById(id).orElse(null);
		return commentRepository.findByBoard(board);
	}	
	
// 댓글 등록	
	@PostMapping("/api/Comment")
	public Comment insertComment(@RequestParam int member_id, @RequestParam String board_id, Comment comment) {
		Member findMember = memberRepository.findById(member_id);
		Board findBoard = boardRepository.findById(board_id).orElse(null);
		comment.setMember(findMember);
		comment.setBoard(findBoard);
		
		return commentRepository.save(comment); 
	}
	
// 댓글 삭제	
	@DeleteMapping("/api/Comment/{id}")
	public void deleteComment(@PathVariable int id) {
		commentRepository.deleteById(id);
	}
}
