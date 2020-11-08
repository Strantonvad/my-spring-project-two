alter table `products_pictures` drop FOREIGN KEY `FK_product`;
GO

alter table `pictures` drop FOREIGN KEY `FK_picture_data`;
GO

alter table `pictures` drop key `UK_picture_data`;
GO

alter table `products_pictures` drop FOREIGN KEY `FK_picture`;
GO

drop table `pictures`;
GO

drop table `products_pictures`;
GO

drop table `pictures_data`;
GO
