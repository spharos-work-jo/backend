package com.workjo.pointapp.coupon.vo.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.workjo.pointapp.coupon.domain.CouponType;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class CouponGetRes {

	private Long id;
	private String name;
	private String description;
	private Integer couponValue;
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private String imageUrl;
	private CouponType type;
	private String guideline;
	private String couponNum;
	private Boolean isDownloaded;
	private String partnerImageUrl;
	private String partnerThumbnailUrl;

}
