package com.workjo.pointapp.point.common.domain;

import com.workjo.pointapp.common.domain.BaseDateTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@SuppressWarnings("DefaultAnnotationParam")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "point")
public class Point extends BaseDateTime {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = true, name = "total_point")
    private Integer totalPoint;

    @Column(nullable = false, name = "point")
    private Integer point;

    @Column(nullable = false, name = "type")
    private PointType type;

    @Column(nullable = true, name = "title",length = 30)
    private String title;

    @Column(nullable = false, name = "user_uuid")
    private UUID userUuid;

//    @Setter
//    @Column(nullable = false, name = "is_usable")
//    private boolean isUsable;
}
