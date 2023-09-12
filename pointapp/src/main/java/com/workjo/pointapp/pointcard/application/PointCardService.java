package com.workjo.pointapp.pointcard.application;


import com.workjo.pointapp.pointcard.dto.PointCardDto;

import java.util.UUID;


public interface PointCardService {

	PointCardDto getPointCardByUserUUID(UUID currentUserUUID);

}
