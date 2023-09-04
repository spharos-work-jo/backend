package com.workjo.pointapp.auth.domain;


import com.workjo.pointapp.common.domain.BaseRegDateTime;
import com.workjo.pointapp.user.domain.User;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserOauth extends BaseRegDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 45)
	private String oauthId;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, name = "provider")
	private OauthProviderType provider;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
