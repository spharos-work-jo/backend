package com.workjo.pointapp.point.calculate.usable.plan;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConvertPointUsablePlan is a Querydsl query type for ConvertPointUsablePlan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConvertPointUsablePlan extends EntityPathBase<ConvertPointUsablePlan> {

    private static final long serialVersionUID = 37876645L;

    public static final QConvertPointUsablePlan convertPointUsablePlan = new QConvertPointUsablePlan("convertPointUsablePlan");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> pointAmount = createNumber("pointAmount", Integer.class);

    public final NumberPath<Long> pointId = createNumber("pointId", Long.class);

    public final ComparablePath<java.util.UUID> userUuid = createComparable("userUuid", java.util.UUID.class);

    public QConvertPointUsablePlan(String variable) {
        super(ConvertPointUsablePlan.class, forVariable(variable));
    }

    public QConvertPointUsablePlan(Path<? extends ConvertPointUsablePlan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConvertPointUsablePlan(PathMetadata metadata) {
        super(ConvertPointUsablePlan.class, metadata);
    }

}

