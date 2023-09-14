package com.workjo.pointapp.point.calculate.usable.plan;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointUsableConvertPlan is a Querydsl query type for PointUsableConvertPlan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointUsableConvertPlan extends EntityPathBase<PointUsableConvertPlan> {

    private static final long serialVersionUID = 41578395L;

    public static final QPointUsableConvertPlan pointUsableConvertPlan = new QPointUsableConvertPlan("pointUsableConvertPlan");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> pointAmount = createNumber("pointAmount", Integer.class);

    public final NumberPath<Long> pointId = createNumber("pointId", Long.class);

    public final ComparablePath<java.util.UUID> userUuid = createComparable("userUuid", java.util.UUID.class);

    public QPointUsableConvertPlan(String variable) {
        super(PointUsableConvertPlan.class, forVariable(variable));
    }

    public QPointUsableConvertPlan(Path<? extends PointUsableConvertPlan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointUsableConvertPlan(PathMetadata metadata) {
        super(PointUsableConvertPlan.class, metadata);
    }

}

