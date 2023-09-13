package com.workjo.pointapp.pointcard.dto;


import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointCardDto {

	private Long id;
	private String cardNumber;

}
