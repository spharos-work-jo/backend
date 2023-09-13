package com.workjo.pointapp.pointcard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointCard is a Querydsl query type for PointCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointCard extends EntityPathBase<PointCard> {

    private static final long serialVersionUID = 1211811254L;

    public static final QPointCard pointCard = new QPointCard("pointCard");

    public final com.workjo.pointapp.common.domain.QBaseDateTime _super = new com.workjo.pointapp.common.domain.QBaseDateTime(this);

    public final StringPath cardNumber = createString("cardNumber");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QPointCard(String variable) {
        super(PointCard.class, forVariable(variable));
    }

    public QPointCard(Path<? extends PointCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointCard(PathMetadata metadata) {
        super(PointCard.class, metadata);
    }

}

