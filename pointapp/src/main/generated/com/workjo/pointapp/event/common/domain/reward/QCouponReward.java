package com.workjo.pointapp.event.common.domain.reward;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCouponReward is a Querydsl query type for CouponReward
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouponReward extends EntityPathBase<CouponReward> {

    private static final long serialVersionUID = -1230735897L;

    public static final QCouponReward couponReward = new QCouponReward("couponReward");

    public final QEventReward _super = new QEventReward(this);

    public final NumberPath<Long> couponId = createNumber("couponId", Long.class);

    //inherited
    public final NumberPath<Long> eventId = _super.eventId;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final NumberPath<Integer> numWinners = _super.numWinners;

    //inherited
    public final NumberPath<Integer> ranking = _super.ranking;

    public QCouponReward(String variable) {
        super(CouponReward.class, forVariable(variable));
    }

    public QCouponReward(Path<? extends CouponReward> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCouponReward(PathMetadata metadata) {
        super(CouponReward.class, metadata);
    }

}

