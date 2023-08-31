package com.workjo.pointapp.point.application;

import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.point.application.pointpolicy.IPointPolicy;
import com.workjo.pointapp.point.domain.*;
import com.workjo.pointapp.point.dto.common.PointCreateDto;
import com.workjo.pointapp.point.dto.common.PointEntityDto;
import com.workjo.pointapp.point.dto.gift.PointGiftEntityDto;
import com.workjo.pointapp.point.dto.gift.PointGiftGetUnrepliedDto;
import com.workjo.pointapp.point.dto.history.PointHistoryDto;
import com.workjo.pointapp.point.dto.earn.PointEarnDto;
import com.workjo.pointapp.point.dto.gift.PointGiftCreateDto;
import com.workjo.pointapp.point.dto.gift.PointGiftReplyDto;
import com.workjo.pointapp.point.infrastructure.IPointEarnRepository;
import com.workjo.pointapp.point.infrastructure.IPointGiftRepository;
import com.workjo.pointapp.point.infrastructure.IPointRepository;
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
@RequiredArgsConstructor
@Slf4j
public class PointServiceImple implements IPointService {

    private final IPointRepository pointRepository;
    private final IPointEarnRepository pointEarnRepository;
    private final IPointGiftRepository pointGiftRepository;
    private final UserRepository userRepository;
    private final IPointPolicy pointPolicy;
    private final ModelMapper modelMapper;

//    @Override
////    public List<PointGiftEntityDto> getUnrepliedGifts(PointGiftGetUnrepliedDto dto) {
////        return this.findUnrepliedPointGifts(dto.getUserUuid());
////    }

    @Override //todo 테스트용 코드
    public PointEntityDto addPoint(PointCreateDto dto) {
        return modelMapper.map(
                this.saveTotalRenewedPoint(dto), PointEntityDto.class);
    }

    @Override
    public void givePointGift(PointGiftCreateDto giftCreateDto) {
        PointGiftEntityDto giftEntityDto = giftCreateDto.getPointGiftEntityDto();
        Point sentPoint = this.saveTotalRenewedPoint(
                new PointCreateDto(
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
    public void replyPointGift(PointGiftReplyDto giftReplyDto) {
        //todo check: 프론트에서 미수락 받은 포인트선물 정보 그대로 넘겨주지 않으면 pointGift id로 조회해서 수정해야함
        PointGiftEntityDto repliedGiftDto = giftReplyDto.getPointGiftEntityDto();
        PointCreateDto pointCreateDto = PointCreateDto.builder()
                .point(giftReplyDto.getPoint())
                .pointType(PointType.GIFT)
                .userUuid(repliedGiftDto.getToUserUuid()).build();

        // 선물 응답으로 인한 포인트 생성
        if (repliedGiftDto.getGiftStatus() == PointGiftStatus.ACCEPT) {
            User sentUser = userRepository.findByUUID(repliedGiftDto.getFromUserUuid()).
                    orElseThrow(() -> (new CustomException(ErrorCode.NOT_FOUND_USER)));

            pointCreateDto.setTitle(String.format("%s 님이 보낸선물: ", sentUser.getName()));
            pointCreateDto.setUserUuid(repliedGiftDto.getToUserUuid());

        } else if (repliedGiftDto.getGiftStatus() == PointGiftStatus.REJECT) {
            pointCreateDto.setTitle("보낸 선물거절");
            pointCreateDto.setUserUuid(repliedGiftDto.getFromUserUuid());
        }

        Point receivedPoint = this.saveTotalRenewedPoint(pointCreateDto);
        repliedGiftDto.setReceivedPointId(receivedPoint.getId());

        this.updatePointGift(repliedGiftDto);

    }

    @Override
    public void earnPoint(PointEarnDto earnDto) {//todo 적립 요청된 포인트 정산 처리 schedule 구현
//        todo bill이 포인트 적립에 이미 사용되었는지 확인
        // Bill bill = billRepository.findBillBy(storeId, receiptId, UUID userUuid)
        // if bill.getIsUsedToPoint
        // dto.isSaved = false;
        // return;


        // 포인트 엔티티 생성 및 포인트 테이블 저장
//        int pointAmount =  pointPolicy.getPoint(bill.getPaidPrice);
        int point = pointPolicy.getPoint(earnDto.getPaidPrice());

        PointCreateDto createDto = new PointCreateDto(
                earnDto.getUserUuid(),
                point,
                PointType.EARN,
                PointType.EARN.getCode()/* change later*/
        );
        Point createdPoint = this.saveTotalNotRenewedPoint(createDto);

        earnDto.setPointId(createdPoint.getId());
        earnDto.setReceiptDisplayable(true);/* todo find from bill db*/
        // 포인트 적립 테이블 저장
        PointEarn pointEarn = new PointEarn();
        modelMapper.map(earnDto, pointEarn);

        PointEarn savedEntity = pointEarnRepository.save(pointEarn);//pointEarn dao
        earnDto.setIsSucceeded(savedEntity != null);
    }


    @Override
    public List<PointEntityDto> getPointHistoryOfUser(PointHistoryDto dto) {
        List<Point> pointList = this.findPoint(dto);
        if (pointList == null) {
            return new ArrayList<PointEntityDto>();
        }

        List<PointEntityDto> pointEntityDtoList = pointList.stream().map(
                point -> {
                    PointEntityDto pointEntityDto = new PointEntityDto();
                    modelMapper.map(point, pointEntityDto);
                    return pointEntityDto;
                }
        ).collect(Collectors.toList());

        return pointEntityDtoList;
    }

    private Point saveTotalRenewedPoint(PointCreateDto createDto)//public of dao
    {
        int totalBeforeCreate = getTotalPoint(createDto.getUserUuid());
        log.info("total before gift : " + totalBeforeCreate);
        int totalAfterCreate = createDto.getPoint() + totalBeforeCreate;
        if (totalAfterCreate < 0) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);//todo 에러코드 추가, invalid point gift amount
        }
        log.info("total after gift : " + totalAfterCreate);

        Point point = Point.builder().totalPoint(totalAfterCreate).build();
        modelMapper.map(createDto, point);
//        log.info("\n\n\n mylog");
//        log.info(point.getUserUuid().toString());
//        log.info(String.format("%d", point.getPoint()));
//        log.info(point.getTitle());
//        log.info(String.format("%d", point.getTotalPoint()));
//        log.info(point.getPointType().getValue());


        return this.savePoint(point);
    }

    private Point saveTotalNotRenewedPoint(PointCreateDto createDto)//public of dao
    {
        int totalPoint = getTotalPoint(createDto.getUserUuid());
        Point point = Point.builder().totalPoint(totalPoint).build();
        modelMapper.map(createDto, point);

        return this.savePoint(point);
    }


    //todo refactoring : dao code below, if wanna refactor, search repository used code and separate them all
    // point Dao

    private List<Point> findPoint(PointHistoryDto dto)//public of dao
    {
        return pointRepository.
                findByUserUuidAndPointTypeAndRegDateBetweenOrderByRegDateDesc
                        (dto.getUserUuid(), dto.getPointType(), dto.getHistoryStartDate(), dto.getHistoryEndDate());
    }

    private Point savePoint(Point point) {
        Point savedPoint = pointRepository.save(point);
        if (savedPoint == null) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR/*todo 에러코드쪽 디버깅 되면 POINTSAVEFAILED 에러코드 정의해서 쓰기*/);
        }
        return savedPoint;
    }

    private int getTotalPoint(UUID userUuid) { //todo consider server cache
        Point point = pointRepository.findFirstByUserUuidOrderByRegDateDesc(userUuid)
                .orElse(null);
        if (point == null) {
            return 0;
        }

        return point.getTotalPoint();
    }


    // PointGift Dao
    private List<PointGiftEntityDto> findUnrepliedPointGifts(UUID userUuid) {
        List<PointGift> unrepliedGifts = pointGiftRepository.findByUserUuidAndGiftStatus(userUuid, PointGiftStatus.WAIT);
        if (unrepliedGifts.isEmpty()) {
            return new ArrayList<PointGiftEntityDto>();
        }

        List<PointGiftEntityDto> result = unrepliedGifts.stream().map(
                pointGift -> { return modelMapper.map(pointGift, PointGiftEntityDto.class); }
        ).collect(Collectors.toList());
        return result;
    }

    private PointGiftEntityDto updatePointGift(PointGiftEntityDto dto) {
        return savePointGift(dto);
    }

    private PointGiftEntityDto savePointGift(PointGiftEntityDto dto) {
        PointGift pointGift = modelMapper.map(dto, PointGift.class);
        PointGift saved = pointGiftRepository.save(pointGift);

        if (saved == null) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);//todo 에러코드 추가: ENTITY_SAVE_FAILED
        }
        return modelMapper.map(saved, PointGiftEntityDto.class);
    }

}