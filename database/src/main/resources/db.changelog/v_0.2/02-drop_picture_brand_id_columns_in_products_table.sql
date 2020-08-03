alter table if exists products
       drop column picture varchar(255);

    alter table if exists products
       drop column brand_id int8 not null;