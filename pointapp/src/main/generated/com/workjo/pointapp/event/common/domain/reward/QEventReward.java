package com.workjo.pointapp.event.common.domain.reward;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventReward is a Querydsl query type for EventReward
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventReward extends EntityPathBase<EventReward> {

    private static final long serialVersionUID = 663917879L;

    public static final QEventReward eventReward = new QEventReward("eventReward");

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> numWinners = createNumber("numWinners", Integer.class);

    public final NumberPath<Integer> ranking = createNumber("ranking", Integer.class);

    public QEventReward(String variable) {
        super(EventReward.class, forVariable(variable));
    }

    public QEventReward(Path<? extends EventReward> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventReward(PathMetadata metadata) {
        super(EventReward.class, metadata);
    }

}

