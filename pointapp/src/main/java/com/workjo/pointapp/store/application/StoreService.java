package com.workjo.pointapp.store.application;


import com.workjo.pointapp.store.dto.StoreGetDto;

import java.util.List;


public interface StoreService {

	List<StoreGetDto> getStoreListByLatLng(double bottomLat, double topLat, double leftLng, double rightLng);

}
