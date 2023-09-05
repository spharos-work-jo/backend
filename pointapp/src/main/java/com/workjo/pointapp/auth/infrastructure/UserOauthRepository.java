package com.workjo.pointapp.auth.infrastructure;


import com.workjo.pointapp.auth.domain.OauthProviderType;
import com.workjo.pointapp.auth.domain.UserOauth;
import com.workjo.pointapp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserOauthRepository extends JpaRepository<UserOauth, Long> {

	Optional<UserOauth> findByOauthIdAndProvider(String oauthId, OauthProviderType provider);

	Boolean existsByOauthIdAndProvider(String oauthId, OauthProviderType provider);
	Boolean existsByUserAndProvider(User user, OauthProviderType provider);

}
