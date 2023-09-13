package com.workjo.pointapp.pointcard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPointCardList is a Querydsl query type for PointCardList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointCardList extends EntityPathBase<PointCardList> {

    private static final long serialVersionUID = 2105089908L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPointCardList pointCardList = new QPointCardList("pointCardList");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPointCard pointCard;

    public final com.workjo.pointapp.user.domain.QUser user;

    public QPointCardList(String variable) {
        this(PointCardList.class, forVariable(variable), INITS);
    }

    public QPointCardList(Path<? extends PointCardList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPointCardList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPointCardList(PathMetadata metadata, PathInits inits) {
        this(PointCardList.class, metadata, inits);
    }

    public QPointCardList(Class<? extends PointCardList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pointCard = inits.isInitialized("pointCard") ? new QPointCard(forProperty("pointCard")) : null;
        this.user = inits.isInitialized("user") ? new com.workjo.pointapp.user.domain.QUser(forProperty("user")) : null;
    }

}

