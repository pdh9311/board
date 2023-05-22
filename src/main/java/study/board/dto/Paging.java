package study.board.dto;

import static study.board.config.Constants.PAGING_SIZE;

import org.springframework.data.domain.Page;

import lombok.Data;
import lombok.ToString;



@Data
@ToString
public class Paging {
	
	private int totalPage;	// 총 페이지수
	private int currPage;	// 현재 페이지 번호
	private int frontPage;	// 맨 앞에 보여줄 번호
	private int endPage;	// 맨 뒤에 보여줄 번호
	private boolean isPrev;	// 이전페이지가 존재하는지
	private boolean isNext;	// 다음페이지가 존재하는지
	private int prevPage;	// 이전페이지 번호
	private int nextPage;	// 다음페이지 번호
	
	public Paging(Page<BoardDto> boardPage) {
		
		totalPage = boardPage.getTotalPages();
		currPage = boardPage.getNumber() + 1;
		
		int start = (currPage - 1) / PAGING_SIZE  * PAGING_SIZE;	// 0~9(0), 10~19(10), 20~29(20)
		frontPage = start + 1;
		endPage = Math.min(start + PAGING_SIZE, totalPage);
		
		isPrev = (frontPage == 1) ? false : true;
		isNext = (endPage == totalPage) ? false : true;
		
		prevPage = (isPrev) ? frontPage - 1 : 1;
		nextPage = (isNext) ? endPage + 1 : totalPage;
		
	}

	
	
	

}
