package study.board.dto;

import lombok.Data;
import study.board.domain.Board;

@Data
public class BoardDto {
	private Long id;
	private String title;
	private String content;
	private int views;
	
	public BoardDto(Board board) {
		this.id = board.getId();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.views = board.getViews();
	}
	
}
