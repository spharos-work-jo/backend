package com.workjo.pointapp.coupon.vo.response;


import lombok.Getter;

import java.util.List;


@Getter
public class CouponIdSliceRes {

	List<Long> content;
	Boolean first;
	Boolean last;

}
