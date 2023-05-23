package study.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseTime {
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createTime;
	
	@UpdateTimestamp
	private LocalDateTime updateTime;

}
