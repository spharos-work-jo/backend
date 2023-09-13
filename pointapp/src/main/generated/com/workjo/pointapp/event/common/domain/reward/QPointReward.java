package com.workjo.pointapp.event.common.domain.reward;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointReward is a Querydsl query type for PointReward
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointReward extends EntityPathBase<PointReward> {

    private static final long serialVersionUID = 813033389L;

    public static final QPointReward pointReward = new QPointReward("pointReward");

    public final QEventReward _super = new QEventReward(this);

    //inherited
    public final NumberPath<Long> eventId = _super.eventId;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final NumberPath<Integer> numWinners = _super.numWinners;

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    //inherited
    public final NumberPath<Integer> ranking = _super.ranking;

    public QPointReward(String variable) {
        super(PointReward.class, forVariable(variable));
    }

    public QPointReward(Path<? extends PointReward> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointReward(PathMetadata metadata) {
        super(PointReward.class, metadata);
    }

}

