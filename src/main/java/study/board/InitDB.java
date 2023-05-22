package study.board;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import study.board.domain.Board;
import study.board.repository.BoardRepository;

@Component
@RequiredArgsConstructor
public class InitDB {
	
	private final BoardRepository boardRepository;
	
	@PostConstruct
	public void init() {
		for (int i = 1; i <= 103; i++) {
			Board board = Board.builder()
				.title("title" + i)
				.content("content" + i)
				.views(0)
				.build();
			boardRepository.save(board);
		}
		
	}
	
	

}
