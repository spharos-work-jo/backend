package com.workjo.pointapp.store.infrastructure;


import com.workjo.pointapp.store.domain.FavoriteStore;
import com.workjo.pointapp.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


public interface FavoriteStoreRepository extends JpaRepository<FavoriteStore, Long> {

	@Query("select f from FavoriteStore f join fetch f.store where f.UUID =:uuid and f.store.used = true")
	List<FavoriteStore> getByUUID(@Param("uuid") UUID uuid);

	@Modifying
	@Transactional
	@Query("delete from FavoriteStore f where f.UUID = :uuid and f.store.id = :storeId")
	void deleteByUUIDAndStoreId(@Param("uuid") UUID uuid, @Param("storeId") Long storeId);

	Boolean existsByStoreAndUUID(Store store, UUID uuid);

}
