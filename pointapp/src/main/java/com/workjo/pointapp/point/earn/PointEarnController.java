package com.workjo.pointapp.point.earn;

import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.point.common.application.IPointService;
import com.workjo.pointapp.point.earn.application.IPointEarnService;
import com.workjo.pointapp.point.earn.dto.EarnPointDto;
import com.workjo.pointapp.point.earn.vo.PointEarnReq;
import com.workjo.pointapp.point.earn.vo.PointEarnRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/point/earn")
public class PointEarnController {
    private final IPointService pointService;
    private final ModelMapper modelMapper;
    private IPointEarnService pointEarnService;

    @PostMapping("")
    public ApiResponse<PointEarnRes> earnPoint
            (@RequestBody PointEarnReq request) {

        //todo bill db 연결하기
        //find bill from db,
        // check if already saved point from bill
        // else get paidPrice from bill
        EarnPointDto dto = new EarnPointDto();
        modelMapper.map(request, dto);
        dto.setUserUuid(UUID.fromString(request.getUserUuid()));
        pointEarnService.earnPoint(dto, pointService);

        PointEarnRes response = new PointEarnRes(dto);
        return ApiResponse.ofSuccess(response);
        //todo 어차피 dto 받이서 responseVo 생성해서 ApiResponse 생성해줄거라면, 다형적으로 dto랑 responseVo.class 인자로 받아서 안에서 modelMapper로 자동처리 해주는것도 구현하고 싶다....
        //todo dto super abstract class에 dto의 선언타입.class를 변수로 받아서 들고있고, 이를 이용해 AbstractDtoClass에서 dto가변인자 또는 dto 리스트를 받아 다형적으로 vo생성 메서드 구현

    }
}
