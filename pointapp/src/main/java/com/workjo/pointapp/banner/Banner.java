package com.workjo.pointapp.banner;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "banner")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "order", nullable = false)
    private Short order;

    @Column(name = "type", nullable = false, length = 3)
    private BannerContentsType type;
}
