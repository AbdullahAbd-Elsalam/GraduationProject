create table item_details
(
    status      bit          null,
    created_at  datetime(6)  null,
    id          bigint auto_increment
        primary key,
    description varchar(255) null,
    priority    varchar(255) null
);

create table item
(
    id              int auto_increment
        primary key,
    item_details_id bigint       null,
    user_id         bigint       null,
    title           varchar(255) not null,
    constraint UKrfcl5t7aurvv2gcxd1sfvfb1o
        unique (item_details_id),
    constraint FKp7v96mi9agvw7rdl3qivc6q1f
        foreign key (item_details_id) references item_details (id)
);

