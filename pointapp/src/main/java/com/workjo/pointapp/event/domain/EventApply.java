package com.workjo.pointapp.event.domain;

import com.workjo.pointapp.common.domain.BaseDateTime;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class EventApply extends BaseDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="event_id",nullable = false)
    private Long eventId;

    @Column(name="is_rewarded",nullable = false)
    private Boolean isRewarded;

    @Column(name="user_uuid",nullable = false)
    private UUID userUuid;
}
