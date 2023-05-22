package study.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.board.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
