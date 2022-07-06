package jpa.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jpa.springboot.entity.Board;
import jpa.springboot.entity.Category;
import jpa.springboot.entity.Member;
import jpa.springboot.ids.IdGnrService;
import jpa.springboot.repository.BoardRepository;
import jpa.springboot.repository.CategoryRepository;
import jpa.springboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardRestController {
	
	private final MemberRepository memberRepository;
	private final CategoryRepository categoryRepository;
	private final BoardRepository boardRepository;
	
	
	@Resource(name = "leagueoflegendsBoardIdGnrService")
	private IdGnrService leagueoflegendsBoardIdGnrService;
	
	@Resource(name = "fifaonline4BoardIdGnrService")
	private IdGnrService fifaonline4BoardIdGnrService;
	
	@Resource(name = "suddenattackBoardIdGnrService")
	private IdGnrService suddenattackBoardIdGnrService;
		
	@Resource(name = "battlegroundsBoardIdGnrService")
	private IdGnrService battlegroundsBoardIdGnrService;

	
// 표준프레임워크 idgen 실패	

//	@Resource(name = "leagueoflegendsBoardIdGnrService")
//	private EgovIdGnrService leagueoflegendsBoardIdGnrService;
//	
//	@Resource(name = "fifaonline4BoardIdGnrService")
//	private EgovIdGnrService fifaonline4BoardIdGnrService;
//	
//	@Resource(name = "suddenattackBoardIdGnrService")
//	private EgovIdGnrService suddenattackBoardIdGnrService;
//	
//	@Resource(name = "battlegroundsBoardIdGnrService")
//	private EgovIdGnrService battlegroundsBoardIdGnrService;

// 게시물 전체 조회
	@GetMapping("/api/Board/{categoryEnName}")
	public Map<String, Object>getBoardList(@PageableDefault(size=10) Pageable pageable, @PathVariable String categoryEnName) {
		Category category = categoryRepository.findByEnName(categoryEnName);
		
		Page<Board> page =  boardRepository.findByCategoryOrderByIdDesc(category, pageable);
		
		List<Board> boardList = page.getContent();
		int totalPage = page.getTotalPages();
		int currentPage = page.getNumber() + 1;
		int pageSize = 5; // list size 가 아닌 pagination size	
		
		int modVal = currentPage % pageSize;
		
		// startPage 공식
		int startPage = currentPage / pageSize * pageSize + 1;		
		if (modVal == 0) startPage -= pageSize;
		// endPage 공식
		int endPage = startPage + pageSize - 1;
		if (endPage > totalPage) endPage = totalPage;
		
		Map<String, Object> response = new HashMap<>();
		response.put("boardList", boardList);
		response.put("totalPage", totalPage);
		response.put("currentPage", currentPage);
		response.put("pageSize", pageSize);
		response.put("startPage", startPage);
		response.put("endPage", endPage);
		
		return response;
	}
	
// 게시물 등록	
	@PostMapping("/api/Board/{categoryEnName}")
	public Board insertBoard(@PathVariable String categoryEnName, @RequestParam int member_id, Board board) {
		Category category = categoryRepository.findByEnName(categoryEnName);
		Member findMember = memberRepository.findById(member_id);
		String BoardId = null;
		if(categoryEnName.equals("leagueoflegends")) {
			BoardId = leagueoflegendsBoardIdGnrService.getNextStringId();
		} else if(categoryEnName.equals("fifaonline4")) {
			BoardId = fifaonline4BoardIdGnrService.getNextStringId();
		} else if(categoryEnName.equals("suddenattack")) {
			BoardId = suddenattackBoardIdGnrService.getNextStringId();
		} else if(categoryEnName.equals("battlegrounds")) {
			BoardId = battlegroundsBoardIdGnrService.getNextStringId();
		}
		
		board.setId(BoardId);
		board.setCategory(category);
		board.setMember(findMember);
		return boardRepository.save(board);	
	}
	
// 게시물 삭제	
	@DeleteMapping("/api/Board/{id}")
	public void deleteBoard(@PathVariable String id) {
		boardRepository.deleteById(id);	
	}
	
// 게시물 수정	
	@PutMapping("/api/Board/{id}")
	public void updateBoard(@PathVariable String id, Board board) {
		Board findBoard = boardRepository.findById(id).orElse(null);
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepository.save(findBoard);
	}
}