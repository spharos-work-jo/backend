package com.workjo.pointapp.user.infrastructure;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.workjo.pointapp.user.domain.QUser.user;


@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

	private final JPAQueryFactory queryFactory;


	@Transactional
	@Modifying(clearAutomatically = true)
	public void updateSoftDeleteUserByUUID(UUID uuid) {
		queryFactory
			.update(user)
			.set(user.accountUse, false)
			.set(user.loginId, "-")
			.where(user.UUID.eq(uuid))
			.execute();
	}

}
