package com.workjo.pointapp.point.earn.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "point_earn")
public class PointEarn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "receipt_id")
    private Long receiptId;

    @Column(nullable = false, name = "store_id")
    private Long storeId;

    @Column(nullable = true, name = "point_id")
    private Long pointId;

    @Column(nullable = true, name = "partner_id")
    private Long partnerId;

    @Column(nullable = false, name = "earned_date")
    private LocalDateTime earnedDate;

}
