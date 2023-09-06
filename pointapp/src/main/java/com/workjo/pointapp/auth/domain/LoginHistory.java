package com.workjo.pointapp.auth.domain;


import com.workjo.pointapp.common.domain.BaseRegDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login_history")
public class LoginHistory extends BaseRegDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "BINARY(16)", name = "user_uuid")
	private UUID userUUID;

	@Column(length = 20, name = "access_ip")
	private String accessIp;

}
