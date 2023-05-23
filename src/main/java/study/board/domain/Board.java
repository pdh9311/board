package study.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Board extends BaseTime {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long id;
	
	private String title;	// 제목
		
	@Column(columnDefinition = "TEXT")
	private String content;	// 내용
	
	private int views;	// 조회수
	
	public void updateViews() {
		this.views++; 
	}
	
	public void updateBoard(String title, String content) {
		this.title = title;
		this.content = content;
	}

}
