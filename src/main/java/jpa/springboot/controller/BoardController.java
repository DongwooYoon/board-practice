package jpa.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jpa.springboot.entity.Board;
import jpa.springboot.entity.Category;
import jpa.springboot.repository.BoardRepository;
import jpa.springboot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

	private final CategoryRepository categoryRepository;
	private final BoardRepository boardRepository;
	
	@GetMapping("/board")
	public String boardList(Model model) {
		String categoryEnName = "leagueoflegends";
		Category category = categoryRepository.findByEnName(categoryEnName);
		model.addAttribute("category", category);
		return "boardList";
	}
	
	@GetMapping("/board/lists/{categoryEnName}")
	public String getBoardList(@PathVariable String categoryEnName, Model model) {
		Category category = categoryRepository.findByEnName(categoryEnName);
		model.addAttribute("category", category);
		return "boardList";
	}
	
	@GetMapping("/board/lists/{categoryEnName}/detail/{boardId}")
	public String getBoard(@PathVariable String boardId, Model model) {
		Board board = boardRepository.findById(boardId).orElse(null);
		model.addAttribute("board", board);
		return "board";
	}
	
	@GetMapping("/board/lists/{categoryEnName}/edit/{boardId}")
	public String updateBoard(@PathVariable String boardId, Model model) {
		Board board = boardRepository.findById(boardId).orElse(null);
		model.addAttribute("board", board);
		return "boardUpdate";
	}
	
	@GetMapping("/board/lists/{categoryEnName}/delete/{boardId}")
	public String deleteBoard(@PathVariable String boardId) {
		boardRepository.deleteById(boardId);
		return "redirect:/board/lists/{categoryEnName}";
	}


}
