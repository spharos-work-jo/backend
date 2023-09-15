package com.workjo.pointapp.point.calculate.expire.plan;

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
@Table(name = "point_expire_plan")
public class PointExpirePlan {
    //need no outer value to initiate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "expire_date")
    private LocalDate expireDate;


    //need outer value to initiate
    @Column(nullable = false, name = "point_id")
    private long pointId;

    @Column(nullable = false, name = "point_amount")
    private int pointAmount;

    @Column(nullable = false, name = "user_uuid")
    private UUID userUuid;


    public PointExpirePlan(long pointId, int pointAmount, UUID userUuid) {
        this.expireDate = LocalDate.now().plusMonths(24);

        this.pointId = pointId;
        this.pointAmount = pointAmount;
        this.userUuid = userUuid;
    }


//    //for test
//    public PointExpirePlan(long pointId, int pointAmount, UUID userUuid, LocalDate expireDate) {
//        this.expireDate = expireDate;
//
//        this.pointId = pointId;
//        this.pointAmount = pointAmount;
//        this.userUuid = userUuid;
//    }
}
