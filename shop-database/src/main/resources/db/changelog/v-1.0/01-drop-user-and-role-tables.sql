    alter table roles
       drop index Unique_role;
GO

    alter table users_roles
       drop foreign key FK_role_id;
GO

    alter table users_roles
       drop foreign key FK_user_id;
GO

    drop table roles;
GO

    drop table users;
GO

    drop table users_roles;
GO
