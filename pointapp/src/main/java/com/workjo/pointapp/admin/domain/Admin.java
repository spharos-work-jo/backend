package com.workjo.pointapp.admin.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 45)
	private String loginId;
	@Column(nullable = false, columnDefinition = "BINARY(16)", name = "UUID")
	private UUID UUID;
	@Column(length = 100)
	private String password; // todo: Hashing
	@Column(length = 100)
	private String name;
	@Column(length = 1)
	private Role role;

}
