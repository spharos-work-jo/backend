package com.workjo.pointapp.point.calculate.expire.planner;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Entity
@Slf4j
@Getter
@Builder
@NoArgsConstructor
@Table(name = "point_expire_plan")
public class PointExpirePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "point_id")
    private long pointId;

    @Column(nullable = false, name = "point_amount")
    private int pointAmount;

    @Column(nullable = false, name = "partner_id")
    private long partnerId;

}
