package com.workjo.pointapp.event;

import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.event.dto.EventEntityDto;
import com.workjo.pointapp.event.vo.response.EventRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/point/events")
public class EventController {

    @GetMapping("/ongoing")
    public ApiResponse<List<EventEntityDto>> getOngoingEvents(@RequestParam String sortedBy) {


        return null;
    }
}
