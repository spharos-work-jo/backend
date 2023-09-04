package com.workjo.pointapp.bill.domain;

import com.workjo.pointapp.common.domain.BaseDateTime;
import com.workjo.pointapp.common.domain.BaseRegDateTime;
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
public class Bill extends BaseRegDateTime {
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

    @Column(name = "receipt_displayable", nullable = false)
    private Boolean receiptDisplayable;

    @Column(name = "is_earned", nullable = false)
    private Boolean isEarned;
}
