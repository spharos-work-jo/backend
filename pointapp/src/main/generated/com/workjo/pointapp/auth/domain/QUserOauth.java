package com.workjo.pointapp.auth.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserOauth is a Querydsl query type for UserOauth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserOauth extends EntityPathBase<UserOauth> {

    private static final long serialVersionUID = -794854698L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserOauth userOauth = new QUserOauth("userOauth");

    public final com.workjo.pointapp.common.domain.QBaseRegDateTime _super = new com.workjo.pointapp.common.domain.QBaseRegDateTime(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath oauthId = createString("oauthId");

    public final EnumPath<OauthProviderType> provider = createEnum("provider", OauthProviderType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final com.workjo.pointapp.user.domain.QUser user;

    public QUserOauth(String variable) {
        this(UserOauth.class, forVariable(variable), INITS);
    }

    public QUserOauth(Path<? extends UserOauth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserOauth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserOauth(PathMetadata metadata, PathInits inits) {
        this(UserOauth.class, metadata, inits);
    }

    public QUserOauth(Class<? extends UserOauth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.workjo.pointapp.user.domain.QUser(forProperty("user")) : null;
    }

}

