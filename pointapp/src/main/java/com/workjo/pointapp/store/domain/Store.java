package com.workjo.pointapp.store.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;


@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Store {

	@Column
	Point location;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 45)
	private String storeName;
	@Column(length = 10)
	private String gunguCode;
	@Column(length = 100)
	private String address;
	@Column
	private Integer partnerId;
	@Column(columnDefinition = "tinyint default 1")
	private Boolean used;

}
