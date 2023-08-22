package com.workjo.pointapp.user.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


// TODO: menu column 프론트와 논의 후 추가
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

}
