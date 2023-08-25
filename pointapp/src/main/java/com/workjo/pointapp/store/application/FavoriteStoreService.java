package com.workjo.pointapp.store.application;


import com.workjo.pointapp.store.dto.StoreGetDto;

import java.util.List;
import java.util.UUID;


public interface FavoriteStoreService {

	List<StoreGetDto> getFavoriteStoreListByUserUUID(UUID uuid);

	void createFavoriteStore(Long id, UUID uuid);

}
