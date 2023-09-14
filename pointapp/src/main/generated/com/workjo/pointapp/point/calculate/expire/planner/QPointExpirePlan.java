package com.workjo.pointapp.point.calculate.expire.planner;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointExpirePlan is a Querydsl query type for PointExpirePlan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointExpirePlan extends EntityPathBase<PointExpirePlan> {

    private static final long serialVersionUID = -2064761371L;

    public static final QPointExpirePlan pointExpirePlan = new QPointExpirePlan("pointExpirePlan");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> partnerId = createNumber("partnerId", Long.class);

    public final NumberPath<Integer> pointAmount = createNumber("pointAmount", Integer.class);

    public final NumberPath<Long> pointId = createNumber("pointId", Long.class);

    public QPointExpirePlan(String variable) {
        super(PointExpirePlan.class, forVariable(variable));
    }

    public QPointExpirePlan(Path<? extends PointExpirePlan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointExpirePlan(PathMetadata metadata) {
        super(PointExpirePlan.class, metadata);
    }

}

