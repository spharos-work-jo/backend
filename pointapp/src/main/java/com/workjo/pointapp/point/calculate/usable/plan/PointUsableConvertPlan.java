package com.workjo.pointapp.point.calculate.usable.plan;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@NoArgsConstructor
@Getter
@Table(name = "point_usable_convert_plan")
public class PointUsableConvertPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point_id", nullable = false)
    private long pointId;

    @Column(name = "point_amount", nullable = false)
    private int pointAmount;

    @Column(name = "user_uuid", nullable = false)
    private UUID userUuid;


    @Builder
    public PointUsableConvertPlan(long pointId, int pointAmount, UUID userUuid) {
        this.pointId = pointId;
        this.pointAmount = pointAmount;
        this.userUuid = userUuid;
    }
}
