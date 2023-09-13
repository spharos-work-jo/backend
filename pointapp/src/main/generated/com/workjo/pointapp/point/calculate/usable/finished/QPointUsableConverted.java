package com.workjo.pointapp.point.calculate.usable.finished;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointUsableConverted is a Querydsl query type for PointUsableConverted
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointUsableConverted extends EntityPathBase<PointUsableConverted> {

    private static final long serialVersionUID = 2021182152L;

    public static final QPointUsableConverted pointUsableConverted = new QPointUsableConverted("pointUsableConverted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> pointAmount = createNumber("pointAmount", Integer.class);

    public final NumberPath<Long> pointId = createNumber("pointId", Long.class);

    public final ComparablePath<java.util.UUID> userUuid = createComparable("userUuid", java.util.UUID.class);

    public QPointUsableConverted(String variable) {
        super(PointUsableConverted.class, forVariable(variable));
    }

    public QPointUsableConverted(Path<? extends PointUsableConverted> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointUsableConverted(PathMetadata metadata) {
        super(PointUsableConverted.class, metadata);
    }

}

