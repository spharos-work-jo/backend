package com.workjo.pointapp.alarm.domain;


import com.workjo.pointapp.common.domain.BaseRegDateTime;
import com.workjo.pointapp.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alarm")
public class Alarm extends BaseRegDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "content", length = 100)
	private String content;

	@Column(name = "is_check", columnDefinition = "tinyint default 0")
	private Boolean isCheck;

	@Column(name = "type", length = 1)
	private AlarmType type;

}
