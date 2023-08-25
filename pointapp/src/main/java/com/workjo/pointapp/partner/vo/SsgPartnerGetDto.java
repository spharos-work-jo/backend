package com.workjo.pointapp.partner.vo;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class SsgPartnerGetDto {

	private String partnerName;
	private String description;
	private String homeUrl;
	private String imageUrl;

}
