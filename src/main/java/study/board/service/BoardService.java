package study.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import study.board.domain.Board;
import study.board.dto.BoardDto;
import study.board.repository.BoardRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;

	// 게시판 생성 create
	public void save(String title, String content) {
		Board board = Board.builder()
				.title(title)
				.content(content)
				.build();
		boardRepository.save(board);		
	}
	
	// 게시판 상세보기 read
	public BoardDto findById(Long id) {
		Board board = boardRepository.findById(id).get();
		board.updateViews();
		return new BoardDto(board);
	}
	
	// 게시판 목록 가져오기
	public Page<BoardDto> findAll(Pageable pageable) {		
		return boardRepository.findAll(pageable)
				.map(BoardDto::new);
				
	}
	
	// 게시판 수정 update
	public void update(Long boardId, String title, String content) {
		Board board = boardRepository.findById(boardId).get();
		board.updateBoard(title, content);
	}
	
	// 게시판 삭제 delete
	public void delete(Long boardId) {
		boardRepository.deleteById(boardId);
	}

}
