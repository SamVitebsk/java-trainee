create database if not exists shop;
use shop;
create table if not exists countries
(
    id bigint unsigned auto_increment,
    name varchar(255) not null,
    constraint countries_id_uindex
        unique (id),
    constraint countries_name_uindex
        unique (name),
    constraint id
        unique (id)
);

alter table countries
    add primary key (id);

create table if not exists customers
(
    id bigint unsigned auto_increment,
    login varchar(255) not null,
    password varchar(255) not null,
    constraint customers_id_uindex
        unique (id),
    constraint customers_login_uindex
        unique (login)
)
    charset=utf8;

alter table customers
    add primary key (id);

create table if not exists bucket
(
    id bigint unsigned auto_increment,
    user_id bigint unsigned not null,
    constraint bucket_user_id_uindex
        unique (user_id),
    constraint id
        unique (id),
    constraint bucket_customers__fk
        foreign key (user_id) references customers (id)
);

alter table bucket
    add primary key (id);

create table if not exists orders
(
    id bigint unsigned auto_increment,
    user_Id bigint unsigned not null,
    created_at datetime not null,
    total double not null,
    accepted tinyint(1) not null,
    constraint id
        unique (id),
    constraint orders_customers__fk
        foreign key (user_Id) references customers (id)
)
    charset=utf8;

alter table orders
    add primary key (id);

create definer = admin@localhost trigger create_order
    after insert
    on orders
    for each row
BEGIN
    INSERT INTO orders_history SET
                                   order_id = new.id,
                                   updated_at = now(),
                                   created_at = new.created_at,
                                   total = new.total,
                                   accepted = new.accepted;
END;

create definer = admin@localhost trigger update_order
    after update
    on orders
    for each row
BEGIN
    INSERT INTO orders_history SET
                                   order_id = new.id,
                                   updated_at = now(),
                                   created_at = new.created_at,
                                   total = new.total,
                                   accepted = new.accepted;
END;

create table if not exists orders_history
(
    id bigint unsigned auto_increment,
    created_at datetime not null,
    updated_at datetime not null,
    total double not null,
    accepted tinyint(1) not null,
    order_id bigint unsigned not null,
    constraint id
        unique (id)
);

alter table orders_history
    add primary key (id);

create table if not exists products
(
    id bigint unsigned auto_increment,
    name varchar(255) null,
    price double null,
    category varchar(255) not null,
    country_id bigint unsigned not null,
    constraint id
        unique (id),
    constraint products_name_uindex
        unique (name),
    constraint products_countries__fk
        foreign key (country_id) references countries (id)
)
    charset=utf8;

alter table products
    add primary key (id);

create table if not exists bucket_product
(
    bucket_id bigint unsigned not null,
    product_id bigint unsigned not null,
    count bigint unsigned not null,
    primary key (bucket_id, product_id),
    constraint bucket_product_bucket__fk
        foreign key (bucket_id) references bucket (id),
    constraint bucket_product_products__fk
        foreign key (product_id) references products (id)
);

create table if not exists warehouse
(
    product_id bigint unsigned null,
    count bigint not null,
    constraint warehouse_products__fk
        foreign key (product_id) references products (id)
)
    charset=utf8;

create definer = admin@localhost procedure get_orders(IN id varchar(255))
SELECT * FROM orders WHERE user_id = id;

