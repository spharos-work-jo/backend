package com.workjo.pointapp.user.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "BINARY(16)", name = "UUID")
	private UUID userUUID;

	@Column
	private Integer menu1;
	@Column
	private Integer menu2;
	@Column
	private Integer menu3;
	@Column
	private Integer menu4;
	@Column
	private Integer menu5;
	@Column
	private Integer menu6;

}
