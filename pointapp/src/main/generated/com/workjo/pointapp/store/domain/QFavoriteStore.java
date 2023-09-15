package com.workjo.pointapp.store.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFavoriteStore is a Querydsl query type for FavoriteStore
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavoriteStore extends EntityPathBase<FavoriteStore> {

    private static final long serialVersionUID = -161900038L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFavoriteStore favoriteStore = new QFavoriteStore("favoriteStore");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStore store;

    public final ComparablePath<java.util.UUID> UUID = createComparable("UUID", java.util.UUID.class);

    public QFavoriteStore(String variable) {
        this(FavoriteStore.class, forVariable(variable), INITS);
    }

    public QFavoriteStore(Path<? extends FavoriteStore> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFavoriteStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFavoriteStore(PathMetadata metadata, PathInits inits) {
        this(FavoriteStore.class, metadata, inits);
    }

    public QFavoriteStore(Class<? extends FavoriteStore> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

