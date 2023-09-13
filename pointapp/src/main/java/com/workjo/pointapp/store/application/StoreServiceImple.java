package com.workjo.pointapp.store.application;


import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.partner.application.SSGPartnerService;
import com.workjo.pointapp.store.domain.Store;
import com.workjo.pointapp.store.dto.StoreGetDto;
import com.workjo.pointapp.store.infrastructure.StoreRepository;
import com.workjo.pointapp.store.util.MapUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImple implements StoreService {

	private final ModelMapperBean modelMapperBean;
	private final StoreRepository storeRepository;
	private final SSGPartnerService ssgPartnerService;


	@Override
	@Transactional(readOnly = true)
	public List<StoreGetDto> getStoreListByLatLng(double bottomLat, double topLat, double leftLng, double rightLng) {
		List<StoreGetDto> resultList;
		String polygonString = MapUtils.boundToPolygonString(bottomLat, topLat, leftLng, rightLng);
		List<Store> storeList = storeRepository.getByBound(polygonString);

		if (storeList == null || storeList.isEmpty()) {
			resultList = Collections.emptyList();
		} else {
			resultList = storeList.stream()
				.map(o -> modelMapperBean.privateStrictModelMapper().map(o, StoreGetDto.class))
				.toList();
			List<Integer> idList = resultList.stream().map(StoreGetDto::getPartnerId).toList();
			resultList = StoreGetDto.setDtoListPartnerImageByPartnerList(resultList, ssgPartnerService.getSsgPartnerSimpleListByIdList(idList));
			resultList.forEach(StoreGetDto::setLaLoByLocation);
		}
		return resultList;
	}

}
