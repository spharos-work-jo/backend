package com.workjo.pointapp.point.earn.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointEarn is a Querydsl query type for PointEarn
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointEarn extends EntityPathBase<PointEarn> {

    private static final long serialVersionUID = 1185011268L;

    public static final QPointEarn pointEarn = new QPointEarn("pointEarn");

    public final DateTimePath<java.time.LocalDateTime> earnedDate = createDateTime("earnedDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> partnerId = createNumber("partnerId", Long.class);

    public final NumberPath<Long> pointId = createNumber("pointId", Long.class);

    public final NumberPath<Long> receiptId = createNumber("receiptId", Long.class);

    public final NumberPath<Long> storeId = createNumber("storeId", Long.class);

    public QPointEarn(String variable) {
        super(PointEarn.class, forVariable(variable));
    }

    public QPointEarn(Path<? extends PointEarn> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointEarn(PathMetadata metadata) {
        super(PointEarn.class, metadata);
    }

}

