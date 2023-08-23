package com.workjo.pointapp.store.dto;


import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreGetDto {

	private String storeName;
	private String sido;
	private String gungu;
	private String detailAddress;
	private Long partnerId;
	private Boolean used;

}
