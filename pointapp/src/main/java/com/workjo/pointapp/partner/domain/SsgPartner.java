package com.workjo.pointapp.partner.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ssg_partner")
public class SsgPartner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 45, name = "partner_name")
	private String partnerName;
	@Column(length = 200, name = "description")
	private String description;
	@Column(length = 200, name = "home_url")
	private String homeUrl;
	@Column(length = 255, name = "image_url")
	private String imageUrl;
	@Column(name = "is_provide_receipt", columnDefinition = "tinyint default 1")
	private Boolean isProvideReceipt;
	@Column(name = "is_accum_point", columnDefinition = "tinyint default 1")
	private Boolean isAccumPoint;

}
