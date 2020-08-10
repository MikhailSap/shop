

create table brands (
       id  bigserial not null,
        name varchar(255),
        primary key (id)
    );


alter table if exists products
       add constraint FKa3a4mpsfdf4d2y6r8ra3sc8mv
       foreign key (id)
       references brands;