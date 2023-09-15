package com.workjo.pointapp.event.common.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventContentsImage is a Querydsl query type for EventContentsImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventContentsImage extends EntityPathBase<EventContentsImage> {

    private static final long serialVersionUID = -925260492L;

    public static final QEventContentsImage eventContentsImage = new QEventContentsImage("eventContentsImage");

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public QEventContentsImage(String variable) {
        super(EventContentsImage.class, forVariable(variable));
    }

    public QEventContentsImage(Path<? extends EventContentsImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventContentsImage(PathMetadata metadata) {
        super(EventContentsImage.class, metadata);
    }

}

