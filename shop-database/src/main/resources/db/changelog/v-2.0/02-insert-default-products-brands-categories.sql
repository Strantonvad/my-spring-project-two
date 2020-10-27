INSERT INTO `brands` (`title`)
    VALUE   ('HP'),
    ('SAMSUNG');
GO

INSERT INTO `categories` (`title`)
    VALUE   ('computers'),
    ('appliances');
GO

INSERT INTO `products` (`title`, `cost`, `brand_id`, `category_id`)
    VALUE   ('laptop', 525.99, 1, 1),
    ('mixer', 99.99, 2, 2);
GO
