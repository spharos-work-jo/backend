package com.workjo.pointapp.point.calculate.expire.plan;

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

    private static final long serialVersionUID = -72286518L;

    public static final QPointExpirePlan pointExpirePlan = new QPointExpirePlan("pointExpirePlan");

    public final DatePath<java.time.LocalDate> expireDate = createDate("expireDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> pointAmount = createNumber("pointAmount", Integer.class);

    public final NumberPath<Long> pointId = createNumber("pointId", Long.class);

    public final ComparablePath<java.util.UUID> userUuid = createComparable("userUuid", java.util.UUID.class);

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

