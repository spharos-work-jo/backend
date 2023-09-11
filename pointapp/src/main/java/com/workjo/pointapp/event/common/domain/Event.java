package com.workjo.pointapp.event.common.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@SuppressWarnings("DefaultAnnotationParam")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "partner_id")
    private Long partnerId;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "draw_date", nullable = true)
    private LocalDateTime drawDate;

    @Column(name = "thumbnail_url", nullable = true)
    private String thumbnailUrl;

//    @Column(name = "status", length = 1)
//    private String status;

}
