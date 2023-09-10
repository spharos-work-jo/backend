package com.workjo.pointapp.event.apply.domain;

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
@Table(name = "event_apply")
public class EventApply extends BaseDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    private EventApplyStatus status;

    @Column(name = "user_uuid", nullable = false)
    private UUID userUuid;

    @Column(name = "event_id", nullable = false)
    private Long eventId;
}
