package com.workjo.pointapp.user.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50, name = "UUID")
	private String UUID;

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
