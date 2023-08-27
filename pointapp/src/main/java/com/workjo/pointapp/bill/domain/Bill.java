package com.workjo.pointapp.bill.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paid_price")
    private int paidPrice;

    @Column(name = "store_id", nullable = false)
    private long storeId;

    @Column(name = "partner_id", nullable = false)
    private long partnerId;

    @Column(name = "user_uuid", nullable = false)
    private UUID userUuid;

}
