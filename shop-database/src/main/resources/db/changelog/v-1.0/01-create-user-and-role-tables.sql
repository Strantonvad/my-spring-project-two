    create table roles (
       id bigint not null auto_increment,
        name varchar(253) not null,
        primary key (id)
    ) engine=InnoDB;
GO

    create table users (
       id bigint not null auto_increment,
        age integer,
        email varchar(255),
        name varchar(32) not null,
        password varchar(128) not null,
        primary key (id)
    ) engine=InnoDB;
GO

    create table users_roles (
       user_id bigint not null,
        role_id bigint not null,
        primary key (user_id, role_id)
    ) engine=InnoDB;
GO

    alter table roles
       add constraint Unique_role unique (name);
GO

    alter table users_roles
       add constraint FK_role_id
       foreign key (role_id)
       references roles (id);
GO

    alter table users_roles
       add constraint FK_user_id
       foreign key (user_id)
       references users (id);
GO
