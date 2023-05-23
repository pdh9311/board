package study.board.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.board.dto.BoardDto;
import study.board.dto.BoardModifyForm;
import study.board.dto.BoardWriteForm;
import study.board.dto.Paging;
import study.board.service.BoardService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/boards")
	public String boardList(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			Model model) {
		
		// PageRequest 생성
		page = (page <= 0) ? 1 : page;
		PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.Direction.DESC, "id");
		
		// 페이징된 board 조회
		Page<BoardDto> boardPage = boardService.findAll(pageRequest);
		
		// 페이징 후 조회된 데이터 꺼내기
		List<BoardDto> boardList = boardPage.getContent();
		
		// Thymeleaf 에서 좀 더 편하게 조건을 확인하기위한 데이터를 가공
		Paging paging = new Paging(boardPage);
		
		// view로 데이터 전달
		model.addAttribute("boardList", boardPage.getContent());		
		model.addAttribute("paging", paging);
		
		log.info("boardList = {}", boardList);
		log.info("paging = {}", paging);
		return "board/list";
	}
	
	@GetMapping("/board/write")
	public String boardWriteForm(@ModelAttribute BoardWriteForm form) {
		return "board/write";
	}
	
	@PostMapping("/board/write")
	public String boardWrite(@ModelAttribute BoardWriteForm form) {
		Long id = boardService.save(form.getTitle(), form.getContent());
		return "redirect:/board/" + id;
	}
	
	@GetMapping("/board/{id}")
	public String boardDetail(
			@PathVariable("id") Long id,
			Model model) {
		BoardDto boardDto = boardService.findById(id);
		model.addAttribute("board", boardDto);
		return "board/detail";
	}
	
	@DeleteMapping("/board/{id}")
	@ResponseBody
	public ResponseEntity<String> boardDelete(@PathVariable("id") Long id) {
		boardService.delete(id);
		return ResponseEntity.ok().body("board num = " + id + "가 삭제되었습니다.");
	}
	
	@GetMapping("/board/modify/{id}")
	public String boardModifyForm(
			@PathVariable("id") Long id,
			Model model) {
		BoardDto boardDto = boardService.findById(id);
		model.addAttribute("board", boardDto);
		return "board/modify";
	}
	
	@PutMapping("/board/modify/{id}")
	public String boardModify(
			@PathVariable("id") Long id,
			@ModelAttribute BoardModifyForm form) {
		boardService.update(id, form.getTitle(), form.getContent());
		return "redirect:/board/" + id;
	}
	
}
