package com.workjo.pointapp.store.vo;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class StoreGetOut {

	private String storeName;
	private String sido;
	private String gungu;
	private String detailAddress;
	private Integer partnerId;
	private String imageUrl;

}
