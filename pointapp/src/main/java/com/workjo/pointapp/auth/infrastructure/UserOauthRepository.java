package com.workjo.pointapp.auth.infrastructure;


import com.workjo.pointapp.auth.domain.UserOauth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserOauthRepository extends JpaRepository<UserOauth, Long> {

	Optional<UserOauth> findByOauthId(String oauthId);

}
