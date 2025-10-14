package com.farm2pot.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1748461957L;

    public static final QUser user = new QUser("user");

    public final DatePath<java.util.Date> birthDay = createDate("birthDay", java.util.Date.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath loginType = createString("loginType");

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final StringPath phoneNo = createString("phoneNo");

    public final ListPath<String, StringPath> roles = this.<String, StringPath>createList("roles", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

