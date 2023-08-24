package com.workjo.pointapp.partner.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SsgPartner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 45)
	private String partnerName;
	@Column(length = 200)
	private String description;
	@Column(length = 200)
	private String homeUrl;
	@Column(length = 255)
	private String imageUrl;
	@Column(columnDefinition = "tinyint default 1")
	private Boolean isProvideReceipt;

}
