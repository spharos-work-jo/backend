package com.workjo.pointapp.partner.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReceiptPartnerCategory is a Querydsl query type for ReceiptPartnerCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReceiptPartnerCategory extends EntityPathBase<ReceiptPartnerCategory> {

    private static final long serialVersionUID = 1284719872L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReceiptPartnerCategory receiptPartnerCategory = new QReceiptPartnerCategory("receiptPartnerCategory");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> parentId = createNumber("parentId", Integer.class);

    public final QSsgPartner ssgPartner;

    public QReceiptPartnerCategory(String variable) {
        this(ReceiptPartnerCategory.class, forVariable(variable), INITS);
    }

    public QReceiptPartnerCategory(Path<? extends ReceiptPartnerCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReceiptPartnerCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReceiptPartnerCategory(PathMetadata metadata, PathInits inits) {
        this(ReceiptPartnerCategory.class, metadata, inits);
    }

    public QReceiptPartnerCategory(Class<? extends ReceiptPartnerCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ssgPartner = inits.isInitialized("ssgPartner") ? new QSsgPartner(forProperty("ssgPartner")) : null;
    }

}

