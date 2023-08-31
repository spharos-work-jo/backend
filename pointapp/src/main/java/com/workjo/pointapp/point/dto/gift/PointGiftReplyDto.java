package com.workjo.pointapp.point.dto.gift;

import com.workjo.pointapp.point.domain.PointGiftStatus;
import com.workjo.pointapp.point.vo.request.PointGiftReplyReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Data
public class PointGiftReplyDto {
    private Integer point;
    private PointGiftEntityDto pointGiftEntityDto;

//    public static PointGiftReplyDto ofRequest(PointGiftReplyReq request) {
//        PointGiftReplyDto dto = new PointGiftReplyDto();
//        modelMapper.map(request, dto);
//        return
//    }

}
