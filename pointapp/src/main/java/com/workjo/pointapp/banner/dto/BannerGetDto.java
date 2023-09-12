package com.workjo.pointapp.banner.dto;


import com.workjo.pointapp.banner.BannerContentsType;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerGetDto {

	private Long id;

	private String imageUrl;

	private Long eventId;

	private String redirectUrl;

	private Integer orderNum;

	private BannerContentsType type;

}
