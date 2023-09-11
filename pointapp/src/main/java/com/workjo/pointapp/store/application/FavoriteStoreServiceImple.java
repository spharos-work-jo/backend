package com.workjo.pointapp.store.application;


import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.partner.application.SSGPartnerService;
import com.workjo.pointapp.store.domain.FavoriteStore;
import com.workjo.pointapp.store.domain.Store;
import com.workjo.pointapp.store.dto.StoreGetDto;
import com.workjo.pointapp.store.infrastructure.FavoriteStoreRepository;
import com.workjo.pointapp.store.infrastructure.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteStoreServiceImple implements FavoriteStoreService {

	private final ModelMapperBean modelMapperBean;
	private final StoreRepository storeRepository;
	private final FavoriteStoreRepository favoriteStoreRepository;
	private final SSGPartnerService ssgPartnerService;


	@Override
	@Transactional(readOnly = true)
	public List<StoreGetDto> getFavoriteStoreListByUserUUID(UUID uuid) {
		List<StoreGetDto> resultList;
		List<FavoriteStore> favoriteStoreList = favoriteStoreRepository.getByUUID(uuid);

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


	@Override
	@Transactional
	public void createFavoriteStore(Long id, UUID uuid) {
		Store store = storeRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		if (favoriteStoreRepository.existsByStoreAndUUID(store, uuid)) {
			throw new CustomException(ErrorCode.DUPLICATE_FAV_STORE);
		}

		favoriteStoreRepository.save(FavoriteStore.builder()
			.store(store)
			.UUID(uuid)
			.build());
	}


	@Override
	@Transactional
	public void deleteFavoriteStore(Long storeId, UUID uuid) {
		favoriteStoreRepository.deleteByUUIDAndStoreId(uuid, storeId);
	}

}
