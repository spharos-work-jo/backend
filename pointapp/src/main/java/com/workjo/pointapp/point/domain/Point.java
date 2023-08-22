package com.workjo.pointapp.point.domain;

import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "user_uuid")
    private UUID userUuid;

    @Column(nullable = true, name = "total_point")
    private int total_point;

    @Column(nullable = true, name = "point")
    private int point;

    @Column(nullable = false, name = "reg_date")
    private LocalDateTime regDate;

    @Column(nullable = false, name = "type")
    private PointType pointType;

    @Column(nullable = true, name = "title")
    private String title;


}
