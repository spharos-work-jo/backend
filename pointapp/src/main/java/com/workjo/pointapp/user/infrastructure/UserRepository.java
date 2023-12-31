package com.workjo.pointapp.user.infrastructure;


import com.workjo.pointapp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLoginId(String loginId);
	Optional<User> findByUUID(UUID uuid);
	Optional<User> findByPhoneAndName(String phone, String name);
	Boolean existsByLoginId(String loginId);

}