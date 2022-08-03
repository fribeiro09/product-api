create table product
(
    product_id       serial
        constraint product_pk primary key,
    title            varchar(200)   not null,
    description      varchar(8000)  not null,
    status           varchar(1)     not null,
    price            numeric(20, 2) not null,
    price_comparison numeric(20, 2)
);

create table product_tags
(
    product_tags_id serial
        constraint product_tags_pk
        primary key,
    product_id      int          not null
        constraint product_tags_product_product_id_fk
        references product,
    tag             varchar(200) not null
);

create table product_categories
(
    product_categories_id serial
        constraint product_categories_pk
        primary key,
    product_id            int          not null
        constraint product_categories_product_product_id_fk
        references product,
    category              varchar(200) not null
);

