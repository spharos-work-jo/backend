package com.workjo.pointapp.admin.infrastructure;


import com.workjo.pointapp.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByLoginId(String loginId);
	Optional<Admin> findByUUID(String UUID);
	Boolean existsByLoginId(String loginId);

}