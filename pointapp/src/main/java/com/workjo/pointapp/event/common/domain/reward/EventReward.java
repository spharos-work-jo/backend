package com.workjo.pointapp.event.common.domain.reward;


import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@Table(name="event_reward")
public abstract class EventReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "ranking", nullable = false)
    private Integer ranking;

    @Column(name = "num_winners", nullable = false)
    private Integer numWinners;

}
