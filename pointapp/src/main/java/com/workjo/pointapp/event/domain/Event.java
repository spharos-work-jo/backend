package com.workjo.pointapp.event.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "partner_id")
    private Long partnerId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "type",nullable = false)
    private EventType type;
//    @Column(name = "partner_id")
//    @Column(name = "partner_id")
//    @Column(name = "partner_id")
//    @Column(name = "partner_id")
}
