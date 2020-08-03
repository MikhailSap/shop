create table product_picture (
       product_id int8 not null,
        picture_id int8 not null
    );


alter table if exists product_picture
       add constraint FKio9lhx3yxxudan2xjd5a8paxd
       foreign key (picture_id)
       references pictures;


alter table if exists product_picture
       add constraint FK6w4qrqqs1t8be038aeub9hgv3
       foreign key (product_id)
       references products;