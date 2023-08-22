package com.workjo.pointapp.user.infrastructure;


import com.workjo.pointapp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLoginId(String loginId);
	Optional<User> findByUUID(String UUID);
	Optional<User> findByPhoneAndName(String phone, String name);
	Boolean existsByLoginId(String loginId);

	@Query(value = "select UUID from user limit 1", nativeQuery = true)
	String findFirstUser();     // todo: remove

}