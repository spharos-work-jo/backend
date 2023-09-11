package com.workjo.pointapp.store.vo;


import lombok.Getter;


@Getter
public class StoreMapGetOut {

	private Long id;
	private String storeName;
	private String address;
	private Double lat;
	private Double lng;
	private String imageUrl;

}
