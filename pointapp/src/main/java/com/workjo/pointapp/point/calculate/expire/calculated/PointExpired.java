package com.workjo.pointapp.point.calculate.expire.calculated;

import com.workjo.pointapp.point.calculate.expire.plan.PointExpirePlan;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Slf4j
@Getter
@NoArgsConstructor
@Table(name = "point_expired")
public class PointExpired {
    //don't need outer value to initiate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    //need outer value to initiate
    @Column(nullable = false, name = "expire_date")
    private LocalDate expireDate;

    @Column(nullable = false, name = "point_id")
    private long pointId;

    @Column(nullable = false, name = "point_amount")
    private int pointAmount;

    @Column(nullable = false, name = "user_uuid")
    private UUID userUuid;

    @Column(nullable = false, name = "expiredType")
    private PointExpiredType expiredType;


    @Builder
    public PointExpired(long pointId, int pointAmount, LocalDate expireDate, UUID userUuid, PointExpiredType expiredType) {
        this.expireDate = expireDate;

        this.pointId = pointId;
        this.pointAmount = pointAmount;
        this.userUuid = userUuid;
        this.expiredType = expiredType;
    }

    public static PointExpired of(PointExpirePlan plan, PointExpiredType expiredType) {
        return new PointExpired(plan.getPointId(), plan.getPointAmount(), plan.getExpireDate(), plan.getUserUuid(),expiredType);
    }

}
