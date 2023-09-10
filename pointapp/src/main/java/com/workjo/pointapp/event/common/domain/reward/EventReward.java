package com.workjo.pointapp.event.common.domain.reward;


import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public abstract class EventReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "rank", nullable = false)
    private int rank;

    @Column(name = "num_winners", nullable = false)
    private int numWinners;

}
