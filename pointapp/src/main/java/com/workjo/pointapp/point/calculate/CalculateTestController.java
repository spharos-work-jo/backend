package com.workjo.pointapp.point.calculate;


import com.workjo.pointapp.point.calculate.usable.ConvertPointUsableCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/point/calculate")
public class CalculateTestController {
    private final ConvertPointUsableCalculator pointUsableConvertCalculator;


    @PostMapping("/convert_usable")
    public void convertTest() {
        pointUsableConvertCalculator.calculateUsableConvertPlan();
    }

//
//    @PostMapping("/convert_usable")
//    public void convertTest() {
//        pointUsableConvertCalculator.calculateUsableConvertPlan();
//    }
}
