package com.workjo.pointapp.point.domain;

import com.spharos.pointapp.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "total_point", columnDefinition = "int default 0")
    private Integer totalPoint;
    @Column(nullable = false, name = "point", columnDefinition = "int default 0")
    private Integer point;
    @Column(nullable = false, name = "used", columnDefinition = "boolean default false")
    private Boolean used;

    @Column(nullable = false)
    @Convert(converter = PointTypeConverter.class)
    private PointType pointType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
