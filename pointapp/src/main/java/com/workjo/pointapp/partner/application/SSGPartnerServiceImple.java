package com.workjo.pointapp.partner.application;


import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.partner.dao.SsgPartnerSimpleDao;
import com.workjo.pointapp.partner.domain.SsgPartner;
import com.workjo.pointapp.partner.infrastructure.SsgPartnerRepository;
import com.workjo.pointapp.partner.vo.SsgPartnerGetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class SSGPartnerServiceImple implements SSGPartnerService {

	private final ModelMapperBean modelMapperBean;

	private final SsgPartnerRepository ssgPartnerRepository;


	@Override
	public List<SsgPartnerGetDto> getSsgPartnerListByIdList(List<Integer> idList) {
		List<SsgPartner> ssgPartnerList = ssgPartnerRepository.findByIdIn(idList);
		return ssgPartnerList.stream()
			.map(ssgPartner -> modelMapperBean.privateStrictModelMapper().map(ssgPartner, SsgPartnerGetDto.class))
			.toList();
	}


	@Override
	public List<SsgPartnerSimpleDao> getSsgPartnerSimpleListByIdList(List<Integer> idList) {
		return ssgPartnerRepository.findIdAndImageUrlByIdIn(idList);
	}


	@Override
	public SsgPartnerGetDto getSsgPartnerById(Integer id) {
		Optional<SsgPartner> ssgPartner = ssgPartnerRepository.findById(id);
		return ssgPartner.map(o ->
				modelMapperBean.privateStrictModelMapper().map(o, SsgPartnerGetDto.class))
			.orElse(null);
	}

}
