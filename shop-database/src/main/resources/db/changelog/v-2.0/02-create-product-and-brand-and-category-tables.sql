create table products
(
    id bigint not null auto_increment,
    title varchar(32) not null,
    cost float not null,
    brand_id bigint,
    category_id bigint,
    primary key (id)
) engine = InnoDB;
GO

create table brands
(
    id bigint not null auto_increment,
    title varchar(32) not null,
    primary key (id)
) engine = InnoDB;
GO

create table categories
(
    id bigint not null auto_increment,
    title varchar(32) not null,
    primary key (id)
) engine = InnoDB;
GO

alter table brands
     add constraint Unique_brand unique (title);
GO

alter table categories
     add constraint Unique_category unique (title);
GO

alter table products
       add constraint FK_brand_id
       foreign key (brand_id)
       references brands (id);
GO

alter table products
       add constraint FK_category_id
       foreign key (category_id)
       references categories (id);
GO
