package com.workjo.pointapp.banner;


import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Table(name = "banner")
public class Banner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "image_url", nullable = false)
	private String imageUrl;

	@Column(name = "event_id", nullable = false)
	private Long eventId;

	@Column(name = "redirect_url", nullable = false)
	private String redirectUrl;

	@Column(name = "order_num", nullable = false)
	private Integer orderNum;

	@Column(name = "type", nullable = false, length = 3)
	private BannerContentsType type;

}
