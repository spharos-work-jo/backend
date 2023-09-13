package com.workjo.pointapp.user.infrastructure;


import java.util.UUID;


public interface UserCustomRepository {

	void updateSoftDeleteUserByUUID(UUID uuid);

}
