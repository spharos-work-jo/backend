package com.workjo.pointapp.store.domain;


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
public class AddressGungu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 5, columnDefinition = "CHAR(5)")
	private String code;

	@Column(length = 10)
	private String sido;

	@Column(length = 10)
	private String gungu;

}
