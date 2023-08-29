package com.workjo.pointapp.point.domain;

import com.workjo.pointapp.common.domain.BaseDateTime;
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
public class PointGift extends BaseDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, name = "message")
    private String message;

    @Column(nullable = false, name = "gift_status")
    private char giftStatus;

    @Column(nullable = false, name = "sent_point_id")
    private Long sentPointId;

    @Column(nullable = false, name = "given_point_id")
    private Long givenPointId;

}