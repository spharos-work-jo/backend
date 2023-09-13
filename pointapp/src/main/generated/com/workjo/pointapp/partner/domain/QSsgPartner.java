package com.workjo.pointapp.partner.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSsgPartner is a Querydsl query type for SsgPartner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSsgPartner extends EntityPathBase<SsgPartner> {

    private static final long serialVersionUID = 1511640115L;

    public static final QSsgPartner ssgPartner = new QSsgPartner("ssgPartner");

    public final StringPath description = createString("description");

    public final StringPath homeUrl = createString("homeUrl");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final BooleanPath isAccumPoint = createBoolean("isAccumPoint");

    public final BooleanPath isProvideReceipt = createBoolean("isProvideReceipt");

    public final StringPath partnerName = createString("partnerName");

    public QSsgPartner(String variable) {
        super(SsgPartner.class, forVariable(variable));
    }

    public QSsgPartner(Path<? extends SsgPartner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSsgPartner(PathMetadata metadata) {
        super(SsgPartner.class, metadata);
    }

}

