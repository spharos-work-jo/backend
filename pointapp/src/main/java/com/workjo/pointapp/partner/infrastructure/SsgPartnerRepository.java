package com.workjo.pointapp.partner.infrastructure;


import com.workjo.pointapp.partner.dao.SsgPartnerSimpleDao;
import com.workjo.pointapp.partner.domain.SsgPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SsgPartnerRepository extends JpaRepository<SsgPartner, Integer> {

	List<SsgPartner> findByIdIn(List<Integer> idList);
	@Query("select s.id as id, s.imageUrl as imageUrl from SsgPartner s where s.id in (:idList) order by s.id")
	List<SsgPartnerSimpleDao> findIdAndImageUrlByIdIn(List<Integer> idList);

}
