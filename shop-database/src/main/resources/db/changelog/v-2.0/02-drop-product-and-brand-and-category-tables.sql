alter table brands
    drop index Unique_brand;
GO

alter table categories
    drop index Unique_category;
GO

alter table products
    drop foreign key FK_brand_id;
GO

alter table products
    drop foreign key FK_category_id;
GO

drop table products;
GO

drop table brands;
GO

drop table categories;
GO
