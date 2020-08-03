
    create table roles (
       id  bigserial not null,
        name varchar(255),
        primary key (id)
    );




    create table user_role (
       user_id int8 not null,
        role_id int8 not null
    );




    create table usrs (
       id  bigserial not null,
        name varchar(255),
        password varchar(255),
        primary key (id)
    );




    alter table if exists user_role
       add constraint FKt7e7djp752sqn6w22i6ocqy6q
       foreign key (role_id)
       references roles;




    alter table if exists user_role
       add constraint FKj6q10q8158i3jfoeilrjlee6k
       foreign key (user_id)
       references usrs;

