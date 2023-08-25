package com.workjo.pointapp.store.application;


import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.partner.application.SSGPartnerService;
import com.workjo.pointapp.store.domain.FavoriteStore;
import com.workjo.pointapp.store.dto.StoreGetDto;
import com.workjo.pointapp.store.infrastructure.FavoriteStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteStoreServiceImple implements FavoriteStoreService {

	private final ModelMapperBean modelMapperBean;

	private final FavoriteStoreRepository favoriteStoreRepository;
	private final SSGPartnerService ssgPartnerService;


	@Override
	public List<StoreGetDto> getFavoriteStoreListByUserUUIDString(String uuidString) {
		List<StoreGetDto> resultList;
		List<FavoriteStore> favoriteStoreList = favoriteStoreRepository.getByUUID(UUID.fromString(uuidString));

		if (favoriteStoreList == null || favoriteStoreList.isEmpty()) {
			resultList = Collections.emptyList();
		} else {
			resultList = favoriteStoreList.stream()
				.map(favoriteStore -> modelMapperBean.privateStrictModelMapper().map(favoriteStore.getStore(), StoreGetDto.class))
				.toList();
			List<Integer> idList = resultList.stream().map(StoreGetDto::getPartnerId).toList();
			resultList = StoreGetDto.setDtoListPartnerImageByPartnerList(resultList, ssgPartnerService.getSsgPartnerSimpleListByIdList(idList));
		}
		return resultList;
	}

}
