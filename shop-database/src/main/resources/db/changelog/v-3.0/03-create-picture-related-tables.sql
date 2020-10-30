CREATE TABLE `pictures_data`
(
    `id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `data` longblob   NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;
GO

CREATE TABLE `pictures`
(
    `id`              bigint(20)   NOT NULL AUTO_INCREMENT,
    `content_type`    varchar(255) NOT NULL,
    `name`            varchar(255) NOT NULL,
    `picture_data_id` bigint(20)   NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_picture_data` (`picture_data_id`),
    CONSTRAINT `FK_picture_data` FOREIGN KEY (`picture_data_id`) REFERENCES `pictures_data` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = latin1;
GO

CREATE TABLE `products_pictures`
(
    `product_id` bigint(20) NOT NULL,
    `picture_id` bigint(20) NOT NULL,
    KEY `FK_product` (`product_id`),
    KEY `FK_picture` (`picture_id`),
    CONSTRAINT `FK_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
    CONSTRAINT `FK_picture` FOREIGN KEY (`picture_id`) REFERENCES `pictures` (`id`)
) ENGINE = InnoDB
GO
