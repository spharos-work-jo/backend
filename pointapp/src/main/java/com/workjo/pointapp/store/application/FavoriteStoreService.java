package com.workjo.pointapp.store.application;


import com.workjo.pointapp.store.dto.StoreGetDto;

import java.util.List;


public interface FavoriteStoreService {

	List<StoreGetDto> getFavoriteStoreListByUserUUIDString(String uuidString);

}
