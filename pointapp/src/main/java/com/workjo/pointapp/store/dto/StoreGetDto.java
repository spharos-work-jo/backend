package com.workjo.pointapp.store.dto;


import com.workjo.pointapp.partner.dao.SsgPartnerSimpleDao;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreGetDto {

	private Long id;
	private String storeName;
	private String gunguCode;
	private String address;
	private Integer partnerId;
	private Boolean used;
	private String imageUrl;

	private Point location;
	private Double lat;
	private Double lng;


	public static List<StoreGetDto> setDtoListPartnerImageByPartnerList(List<StoreGetDto> storeDtoList, List<SsgPartnerSimpleDao> partnerImageList) {
		if (partnerImageList != null && !partnerImageList.isEmpty()) {
			storeDtoList = storeDtoList.stream()
				.sorted(Comparator.comparing(StoreGetDto::getPartnerId, Comparator.nullsLast(Comparator.naturalOrder())))
				.collect(Collectors.toList());

			int idx = 0;
			for (StoreGetDto dto : storeDtoList) {
				if (!dto.getPartnerId().equals(partnerImageList.get(idx).getId())) {
					idx++;
				}
				if (dto.partnerId != null && dto.getPartnerId().equals(partnerImageList.get(idx).getId())) {
					dto.imageUrl = partnerImageList.get(idx).getImageUrl();
				}
			}
		}
		return storeDtoList;
	}


	public void setLaLoByLocation() {
		this.lat = location.getY();
		this.lng = location.getX();
	}

}
