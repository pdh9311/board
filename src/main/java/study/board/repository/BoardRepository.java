package study.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
