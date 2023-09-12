package com.workjo.pointapp.banner.vo;


import com.workjo.pointapp.banner.BannerContentsType;
import lombok.Getter;


@Getter
public class BannerGetRes {

	private Long id;

	private String imageUrl;

	private Long eventId;

	private String redirectUrl;

	private Integer orderNum;

	private BannerContentsType type;

}
