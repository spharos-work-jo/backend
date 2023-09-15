package com.workjo.pointapp.store.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddressGungu is a Querydsl query type for AddressGungu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddressGungu extends EntityPathBase<AddressGungu> {

    private static final long serialVersionUID = -150052187L;

    public static final QAddressGungu addressGungu = new QAddressGungu("addressGungu");

    public final StringPath code = createString("code");

    public final StringPath gungu = createString("gungu");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath sido = createString("sido");

    public QAddressGungu(String variable) {
        super(AddressGungu.class, forVariable(variable));
    }

    public QAddressGungu(Path<? extends AddressGungu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddressGungu(PathMetadata metadata) {
        super(AddressGungu.class, metadata);
    }

}

