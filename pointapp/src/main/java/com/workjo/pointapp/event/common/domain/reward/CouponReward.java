package com.workjo.pointapp.event.common.domain.reward;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;


@Entity
@Getter
@DiscriminatorValue("C")
@Table(name="coupon_reward")
public class CouponReward extends EventReward{

    @Column(name="coupon_id",nullable = false)
    private Long couponId;

}
