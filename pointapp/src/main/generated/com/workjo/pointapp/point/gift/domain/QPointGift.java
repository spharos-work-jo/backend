package com.workjo.pointapp.point.gift.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointGift is a Querydsl query type for PointGift
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointGift extends EntityPathBase<PointGift> {

    private static final long serialVersionUID = -1806552764L;

    public static final QPointGift pointGift = new QPointGift("pointGift");

    public final com.workjo.pointapp.common.domain.QBaseDateTime _super = new com.workjo.pointapp.common.domain.QBaseDateTime(this);

    public final ComparablePath<java.util.UUID> fromUserUuid = createComparable("fromUserUuid", java.util.UUID.class);

    public final EnumPath<PointGiftStatus> giftStatus = createEnum("giftStatus", PointGiftStatus.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> resultPointId = createNumber("resultPointId", Long.class);

    public final NumberPath<Long> sentPointId = createNumber("sentPointId", Long.class);

    public final ComparablePath<java.util.UUID> toUserUuid = createComparable("toUserUuid", java.util.UUID.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QPointGift(String variable) {
        super(PointGift.class, forVariable(variable));
    }

    public QPointGift(Path<? extends PointGift> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointGift(PathMetadata metadata) {
        super(PointGift.class, metadata);
    }

}

