
    create table categories (
       id  bigserial not null,
        name varchar(255),
        primary key (id)
    );

    create table product_category (
       category_id int8 not null,
        product_id int8 not null
    );

    create table products (
       id  bigserial not null,
        description varchar(25),
        name varchar(255),
        price int4 not null,
        primary key (id)
    );

    alter table if exists product_category
       add constraint FK5w81wp3eyugvi2lii94iao3fm
       foreign key (product_id)
       references products;

    alter table if exists product_category
       add constraint FKdswxvx2nl2032yjv609r29sdr
       foreign key (category_id)
       references categories;
