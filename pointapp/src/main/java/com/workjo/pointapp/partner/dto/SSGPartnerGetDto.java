package com.workjo.pointapp.partner.dto;


import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SSGPartnerGetDto {

	private String partnerName;
	private String description;
	private String homeUrl;
	private String imageUrl;

}
