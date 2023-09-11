package com.workjo.pointapp.event.common.domain.reward;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;


@Entity
@Getter
@DiscriminatorValue("P")
@Table(name = "point_reward")
public class PointReward extends EventReward {

    @Column(name = "point", nullable = false)
    private int point;
}
