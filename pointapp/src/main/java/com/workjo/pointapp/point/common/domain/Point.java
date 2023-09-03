package com.workjo.pointapp.point.common.domain;

import com.workjo.pointapp.common.domain.BaseDateTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Point extends BaseDateTime {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "user_uuid")
    private UUID userUuid;

    @Column(nullable = true, name = "total_point")
    private Integer totalPoint;

    @Column(nullable = false, name = "point")
    private Integer point;

    @Column(nullable = false, name = "type")
    private PointType pointType;

    @Column(nullable = true, name = "title")
    private String title;

//    @Column(nullable = false, name = "description")
//    private String description;//todo description 쓸지말지 정하기

}
