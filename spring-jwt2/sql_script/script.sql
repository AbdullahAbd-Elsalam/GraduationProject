create table token_seq
(
    next_val bigint null
);

create table user
(
    enabledd bit                    not null,
    id       int auto_increment
        primary key,
    email    varchar(255)           not null,
    password varchar(255)           null,
    role     enum ('ADMIN', 'USER') null
);

create table otp
(
    id              int auto_increment
        primary key,
    otp             int         not null,
    user_id         int         null,
    expiration_time datetime(6) not null,
    constraint UK4mkxc1wpojj1vymcvurokktwm
        unique (user_id),
    constraint FKdrrkob03otk15fxe9b0bkkp35
        foreign key (user_id) references user (id)
);

create table token
(
    expired    bit             not null,
    id         int             not null
        primary key,
    revoked    bit             not null,
    user_id    int             null,
    token      varchar(255)    null,
    token_type enum ('BEARER') null,
    constraint UKpddrhgwxnms2aceeku9s2ewy5
        unique (token),
    constraint FKe32ek7ixanakfqsdaokm4q9y2
        foreign key (user_id) references user (id)
);

