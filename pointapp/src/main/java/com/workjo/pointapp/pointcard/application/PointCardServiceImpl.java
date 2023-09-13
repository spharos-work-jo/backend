package com.workjo.pointapp.pointcard.application;


import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.pointcard.domain.PointCard;
import com.workjo.pointapp.pointcard.domain.PointCardList;
import com.workjo.pointapp.pointcard.dto.PointCardDto;
import com.workjo.pointapp.pointcard.infrastructure.PointCardListRepository;
import com.workjo.pointapp.pointcard.infrastructure.PointCardRepository;
import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class PointCardServiceImpl implements PointCardService {

	private final static int POINT_CARD_PREFIX = 9350;
	private final static int POINT_CARD_LENGTH = 16;

	private final ModelMapperBean mapperBean;
	private final PointCardListRepository pointCardListRepository;
	private final PointCardRepository pointCardRepository;
	private final UserRepository userRepository;


	@Override
	public PointCardDto getPointCardByUserUUID(UUID uuid) {
		User user = userRepository.findByUUID(uuid).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		PointCardList pointCardList = pointCardListRepository.findByUser(user).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		PointCard pointCard = pointCardList.getPointCard();
		return mapperBean.privateStrictModelMapper().map(pointCard, PointCardDto.class);
	}


	@Override
	public void createPointCardAtSignUp(UUID uuid) {
		User user = userRepository.findByUUID(uuid).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		String pointCardNum = makePointCardNum();
		PointCard pointCard = PointCard.builder()
			.cardNumber(pointCardNum)
			.build();
		pointCard = pointCardRepository.save(pointCard);
		PointCardList pointCardList = PointCardList.builder()
			.user(user)
			.pointCard(pointCard)
			.build();
		pointCardListRepository.save(pointCardList);
	}


	private String makePointCardNum() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long timestampLong = timestamp.getTime();
		// 9350 + 12자리 숫자
		return POINT_CARD_PREFIX + Long.toString(timestampLong).substring(0, POINT_CARD_LENGTH - 4);
	}

}
