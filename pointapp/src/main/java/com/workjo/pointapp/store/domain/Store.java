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
	@Column(length = 45, name = "store_name")
	private String storeName;
	@Column(length = 10, name = "gungu_code")
	private String gunguCode;
	@Column(length = 100, name = "address")
	private String address;
	@Column(name = "partner_id")
	private Integer partnerId;
	@Column(columnDefinition = "tinyint default 1", name = "used")
	private Boolean used;

}
