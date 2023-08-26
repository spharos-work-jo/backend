package com.workjo.pointapp.store.infrastructure;


import com.workjo.pointapp.store.domain.FavoriteStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface FavoriteStoreRepository extends JpaRepository<FavoriteStore, Long> {

	@Query("select f from FavoriteStore f join fetch f.store where f.UUID =:uuid and f.store.used = true")
	List<FavoriteStore> getByUUID(UUID uuid);

}
