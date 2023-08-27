package com.workjo.pointapp.store.infrastructure;


import com.workjo.pointapp.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StoreRepository extends JpaRepository<Store, Long> {

	@Query(value = "select * from store where MBRContains(ST_GeomFromText( :polygon),location)", nativeQuery = true)
	List<Store> getByBound(@Param("polygon") String polygon);

}
