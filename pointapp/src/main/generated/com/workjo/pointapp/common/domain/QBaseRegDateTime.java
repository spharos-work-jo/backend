package com.workjo.pointapp.common.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseRegDateTime is a Querydsl query type for BaseRegDateTime
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseRegDateTime extends EntityPathBase<BaseRegDateTime> {

    private static final long serialVersionUID = 452864869L;

    public static final QBaseRegDateTime baseRegDateTime = new QBaseRegDateTime("baseRegDateTime");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public QBaseRegDateTime(String variable) {
        super(BaseRegDateTime.class, forVariable(variable));
    }

    public QBaseRegDateTime(Path<? extends BaseRegDateTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseRegDateTime(PathMetadata metadata) {
        super(BaseRegDateTime.class, metadata);
    }

}

