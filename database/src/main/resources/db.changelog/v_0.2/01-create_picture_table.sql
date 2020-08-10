create table pictures (
       id  bigserial not null,
        content_type varchar(255),
        data bytea not null,
        primary key (id)
    );