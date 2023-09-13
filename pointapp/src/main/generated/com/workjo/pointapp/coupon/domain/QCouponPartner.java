package com.workjo.pointapp.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCouponPartner is a Querydsl query type for CouponPartner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouponPartner extends EntityPathBase<CouponPartner> {

    private static final long serialVersionUID = -1464883506L;

    public static final QCouponPartner couponPartner = new QCouponPartner("couponPartner");

    public final com.workjo.pointapp.common.domain.QBaseDateTime _super = new com.workjo.pointapp.common.domain.QBaseDateTime(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath thumbnailUrl = createString("thumbnailUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QCouponPartner(String variable) {
        super(CouponPartner.class, forVariable(variable));
    }

    public QCouponPartner(Path<? extends CouponPartner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCouponPartner(PathMetadata metadata) {
        super(CouponPartner.class, metadata);
    }

}

