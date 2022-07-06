package jpa.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import jpa.springboot.entity.Board;
import jpa.springboot.entity.Category;

public interface BoardRepository extends JpaRepository<Board, String>{
	public Page<Board> findByCategoryOrderByIdDesc(Category category, Pageable pageable);
}
