INSERT INTO `users` (`name`, `password`)
VALUE   ('admin', '$2y$12$epJ5Pj7x67ZEWMF9aw.ZlexDiCRidUsyqANAA/Eeb7yO.W8qGhJne'),
        ('guest', '$2y$12$K5VvipdV/W.nZQ4tpjwUD.XisxPXFIK2zJ8273I3yfdSK/m0X0b6a');
GO

INSERT INTO `roles` (`name`)
VALUE ('ROLE_ADMIN'), ('ROLE_GUEST');
GO

INSERT INTO `users_roles`(`user_id`, `role_id`)
SELECT (SELECT id FROM `users` WHERE `name` = 'admin'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM `users` WHERE `name` = 'guest'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_GUEST');
GO
