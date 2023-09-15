package com.workjo.pointapp.point.calculate.expire.calculated;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointExpired is a Querydsl query type for PointExpired
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointExpired extends EntityPathBase<PointExpired> {

    private static final long serialVersionUID = -1909081778L;

    public static final QPointExpired pointExpired = new QPointExpired("pointExpired");

    public final DatePath<java.time.LocalDate> expireDate = createDate("expireDate", java.time.LocalDate.class);

    public final EnumPath<PointExpiredType> expiredType = createEnum("expiredType", PointExpiredType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> pointAmount = createNumber("pointAmount", Integer.class);

    public final NumberPath<Long> pointId = createNumber("pointId", Long.class);

    public final ComparablePath<java.util.UUID> userUuid = createComparable("userUuid", java.util.UUID.class);

    public QPointExpired(String variable) {
        super(PointExpired.class, forVariable(variable));
    }

    public QPointExpired(Path<? extends PointExpired> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointExpired(PathMetadata metadata) {
        super(PointExpired.class, metadata);
    }

}

