package com.workjo.pointapp.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookmark is a Querydsl query type for Bookmark
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookmark extends EntityPathBase<Bookmark> {

    private static final long serialVersionUID = -2075977009L;

    public static final QBookmark bookmark = new QBookmark("bookmark");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> menu1 = createNumber("menu1", Integer.class);

    public final NumberPath<Integer> menu2 = createNumber("menu2", Integer.class);

    public final NumberPath<Integer> menu3 = createNumber("menu3", Integer.class);

    public final NumberPath<Integer> menu4 = createNumber("menu4", Integer.class);

    public final NumberPath<Integer> menu5 = createNumber("menu5", Integer.class);

    public final NumberPath<Integer> menu6 = createNumber("menu6", Integer.class);

    public final ComparablePath<java.util.UUID> userUUID = createComparable("userUUID", java.util.UUID.class);

    public QBookmark(String variable) {
        super(Bookmark.class, forVariable(variable));
    }

    public QBookmark(Path<? extends Bookmark> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookmark(PathMetadata metadata) {
        super(Bookmark.class, metadata);
    }

}

