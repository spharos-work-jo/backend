package com.workjo.pointapp.event.apply.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventApply is a Querydsl query type for EventApply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventApply extends EntityPathBase<EventApply> {

    private static final long serialVersionUID = 2021773112L;

    public static final QEventApply eventApply = new QEventApply("eventApply");

    public final com.workjo.pointapp.common.domain.QBaseDateTime _super = new com.workjo.pointapp.common.domain.QBaseDateTime(this);

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final EnumPath<EventApplyStatus> status = createEnum("status", EventApplyStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final ComparablePath<java.util.UUID> userUuid = createComparable("userUuid", java.util.UUID.class);

    public QEventApply(String variable) {
        super(EventApply.class, forVariable(variable));
    }

    public QEventApply(Path<? extends EventApply> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventApply(PathMetadata metadata) {
        super(EventApply.class, metadata);
    }

}

