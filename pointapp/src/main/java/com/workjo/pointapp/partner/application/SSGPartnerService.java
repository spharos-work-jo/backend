package com.workjo.pointapp.partner.application;


import com.workjo.pointapp.partner.dao.SsgPartnerSimpleDao;
import com.workjo.pointapp.partner.vo.SsgPartnerGetDto;

import java.util.List;


public interface SSGPartnerService {

	List<SsgPartnerGetDto> getSsgPartnerListByIdList(List<Integer> idList);
	List<SsgPartnerSimpleDao> getSsgPartnerSimpleListByIdList(List<Integer> idList);
	SsgPartnerGetDto getSsgPartnerById(Integer id);

}
