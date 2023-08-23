package com.workjo.pointapp.store.infrastructure;


import com.workjo.pointapp.store.domain.FavoriteStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface FavoriteStoreRepository extends JpaRepository<FavoriteStore, Long> {

	List<FavoriteStore> getByUUID(UUID uuid);

}
