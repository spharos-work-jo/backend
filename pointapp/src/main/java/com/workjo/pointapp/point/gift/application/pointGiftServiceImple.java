package com.workjo.pointapp.point.gift.application;

import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.point.common.application.IPointService;
import com.workjo.pointapp.point.common.domain.PointType;
import com.workjo.pointapp.point.common.dto.CreatePointDto;
import com.workjo.pointapp.point.common.dto.PointEntityDto;
import com.workjo.pointapp.point.gift.infrastructure.IPointGiftRepository;
import com.workjo.pointapp.point.gift.domain.PointGift;
import com.workjo.pointapp.point.gift.domain.PointGiftStatus;
import com.workjo.pointapp.point.gift.dto.*;
import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class pointGiftServiceImple implements IPointGiftService {

    private final UserRepository userRepository;
    private final IPointGiftRepository pointGiftRepository;
    private final ModelMapper modelMapper;

    @Override
    public void findReceivedUnrepliedGifts(GetReceivedPointGiftsDto dto, IPointService pointService) {//todo findReceivedGifts로 수정 후 dto(giftStatus필드 추가), dao(giftStatus고려 찾기) 수정
        List<PointGiftEntityDto> receivedGiftsDto =
                this.findReceivedPointGifts(dto.getUserUuid());

        List<PointGiftInfoDto> receivedGiftsInfo = receivedGiftsDto.stream().map(
                receivedGiftDto -> {
                    Integer pointAmount =
                            -pointService.findPointById(receivedGiftDto.getSentPointId()).getPoint();

                    return new PointGiftInfoDto(pointAmount, receivedGiftDto);
                }
        ).collect(Collectors.toList());

        dto.setResult(receivedGiftsInfo);
    }

    @Override
    public void givePointGift(CreatePointGiftDto giftCreateDto, IPointService pointService) {
        PointGiftEntityDto giftEntityDto = giftCreateDto.getPointGiftEntityDto();

        PointEntityDto sentPoint = pointService.saveTotalRenewedPoint(
                new CreatePointDto(
                        giftEntityDto.getFromUserUuid(),
                        -giftCreateDto.getPoint(),
                        PointType.GIFT,
                        String.format("%s에게 선물", giftCreateDto.getToUserName())
                )
        );

        giftEntityDto.setSentPointId(sentPoint.getId());
        this.savePointGift(giftEntityDto);
    }

    @Override
    public void replyPointGift(ReplyPointGiftDto giftReplyDto, IPointService pointService) {
        //todo check: 프론트에서 미수락 받은 포인트선물 정보 그대로 넘겨주지 않으면 pointGift id로 조회해서 수정해야함
        PointGiftEntityDto repliedGiftDto = giftReplyDto.getPointGiftEntityDto();
        CreatePointDto createPointDto = CreatePointDto.builder()
                .point(giftReplyDto.getPoint())
                .pointType(PointType.GIFT)
                .userUuid(repliedGiftDto.getToUserUuid()).build();

        // 선물 응답으로 인한 포인트 생성
        if (repliedGiftDto.getGiftStatus() == PointGiftStatus.ACCEPT) {
            User sentUser = userRepository.findByUUID(repliedGiftDto.getFromUserUuid()).
                    orElseThrow(() -> (new CustomException(ErrorCode.NOT_FOUND_USER)));

            createPointDto.setTitle(String.format("%s 님이 보낸선물: ", sentUser.getName()));
            createPointDto.setUserUuid(repliedGiftDto.getToUserUuid());

        } else if (repliedGiftDto.getGiftStatus() == PointGiftStatus.REJECT) {
            createPointDto.setTitle("보낸 선물거절");
            createPointDto.setUserUuid(repliedGiftDto.getFromUserUuid());
        }

        PointEntityDto receivedPoint = pointService.saveTotalRenewedPoint(createPointDto);
        repliedGiftDto.setResultPointId(receivedPoint.getId());

        this.updatePointGift(repliedGiftDto);

    }

    @Override
    public PointGiftEntityDto updatePointGift(PointGiftEntityDto dto) {
        return savePointGift(dto);
    }

    @Override
    public PointGiftEntityDto savePointGift(PointGiftEntityDto dto) {
        PointGift pointGift = modelMapper.map(dto, PointGift.class);
        PointGift saved = pointGiftRepository.save(pointGift);

        if (saved == null) {
            throw new CustomException(ErrorCode.ENTITY_SAVE_FAILED);
        }
        return modelMapper.map(saved, PointGiftEntityDto.class);
    }


    private List<PointGiftEntityDto> findReceivedPointGifts(UUID toUserUuid) {
        List<PointGift> receivedGifts = pointGiftRepository.findByToUserUuidAndGiftStatus(toUserUuid, PointGiftStatus.WAIT.getCode());
        if (receivedGifts.isEmpty()) {
            return new ArrayList<PointGiftEntityDto>();
        }

        List<PointGiftEntityDto> dtoPointGifts = receivedGifts.stream().map(
                pointGift -> {
                    PointGiftEntityDto dto = modelMapper.map(pointGift, PointGiftEntityDto.class);
                    return dto;
                }
        ).collect(Collectors.toList());

        return dtoPointGifts;
    }
}
