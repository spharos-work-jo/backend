package com.workjo.pointapp.task.scheduler;


import com.workjo.pointapp.coupon.application.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Log4j2
@RequiredArgsConstructor
public class CouponScheduler {

	private final CouponService couponService;


	@Scheduled(cron = "1 0 0 * * *")    // 매일 자정 1초에 실행
	public void deleteOutOfDateAlarm() {
		log.info("[coupon scheduler] start");
		try {
			// endDate가 어제까지인 쿠폰을 만료처리한다.
			List<Long> couponIdList = couponService.findCouponIdEndDateIsYesterDay();
			couponService.modifyCouponIsExpiredByCouponIdList(couponIdList);
			log.info("[coupon scheduler] end");
		} catch (Exception e) {
			log.error(e.getMessage());
			log.info("[coupon scheduler] error");
		}
	}

}
