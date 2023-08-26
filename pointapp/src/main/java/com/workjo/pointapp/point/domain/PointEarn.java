package com.workjo.pointapp.point.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
public class PointEarn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "receipt_id")
    private Long receiptId;

    @Column(nullable = false, name = "paid_price")
    private int paidPrice;

    @Column(nullable = false, name = "store_id")
    private Long storeId;

    @Column(nullable = true, name = "point_id")
    private Long pointId;

    @Column(nullable = true, name = "partner_id")
    private Long partnerId;

    @Column(nullable = false, name = "user_uuid")
    private UUID userUuid;

    @Column(nullable = true, name = "receipt_displayable")
    private boolean receiptDisplayable;
}
