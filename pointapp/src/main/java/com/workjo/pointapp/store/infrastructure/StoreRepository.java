package com.workjo.pointapp.store.infrastructure;


import com.workjo.pointapp.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StoreRepository extends JpaRepository<Store, Long> {
}
