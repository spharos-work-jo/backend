package com.workjo.pointapp.admin.domain;


import com.workjo.pointapp.common.domain.Member;
import com.workjo.pointapp.common.domain.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin extends Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 45)
	private String loginId;
	@Column(nullable = false, length = 100, name = "UUID")
	private String UUID; // todo: UUID
	@Column(length = 100)
	private String password; // todo: Hashing
	@Column(length = 100)
	private String name;
	@Column(length = 1)
	private Role role;

}
