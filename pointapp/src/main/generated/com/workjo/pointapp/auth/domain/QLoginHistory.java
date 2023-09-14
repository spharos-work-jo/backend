package com.workjo.pointapp.auth.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLoginHistory is a Querydsl query type for LoginHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLoginHistory extends EntityPathBase<LoginHistory> {

    private static final long serialVersionUID = 494434145L;

    public static final QLoginHistory loginHistory = new QLoginHistory("loginHistory");

    public final com.workjo.pointapp.common.domain.QBaseRegDateTime _super = new com.workjo.pointapp.common.domain.QBaseRegDateTime(this);

    public final StringPath accessIp = createString("accessIp");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final ComparablePath<java.util.UUID> userUUID = createComparable("userUUID", java.util.UUID.class);

    public QLoginHistory(String variable) {
        super(LoginHistory.class, forVariable(variable));
    }

    public QLoginHistory(Path<? extends LoginHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLoginHistory(PathMetadata metadata) {
        super(LoginHistory.class, metadata);
    }

}

