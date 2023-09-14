package com.workjo.pointapp.point.calculate.usable.calculated;

import com.workjo.pointapp.point.calculate.usable.plan.ConvertPointUsablePlan;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "point_usable_converted")
public class PointUsableConverted {

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
    public PointUsableConverted(long pointId, int pointAmount, UUID userUuid) {
        this.pointId = pointId;
        this.pointAmount = pointAmount;
        this.userUuid = userUuid;
    }

    public static PointUsableConverted of(ConvertPointUsablePlan plan) {
        return new PointUsableConverted(plan.getPointId(), plan.getPointAmount(), plan.getUserUuid());
    }
}
