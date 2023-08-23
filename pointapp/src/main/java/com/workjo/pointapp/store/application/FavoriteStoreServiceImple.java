package com.workjo.pointapp.store.application;


import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.store.domain.FavoriteStore;
import com.workjo.pointapp.store.infrastructure.FavoriteStoreRepository;
import com.workjo.pointapp.store.vo.StoreGetOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteStoreServiceImple implements FavoriteStoreService {

	private final ModelMapperBean modelMapperBean;

	private final FavoriteStoreRepository favoriteStoreRepository;


	@Override
	public List<StoreGetOut> getFavoriteStoreListByUserUUIDString(String uuidString) {
		List<StoreGetOut> resultList;
		List<FavoriteStore> favoriteStoreList = favoriteStoreRepository.getByUUID(UUID.fromString(uuidString));

		if (favoriteStoreList.isEmpty()) {
			resultList = List.of();
		} else {
			resultList = favoriteStoreList.stream()
				.map(favoriteStore -> modelMapperBean.privateStrictModelMapper().map(favoriteStore.getStore(), StoreGetOut.class))
				.toList();
		}
		return resultList;
	}

}
