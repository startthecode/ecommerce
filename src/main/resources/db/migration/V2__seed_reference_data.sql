-- For Status initial data
INSERT INTO status_table (statusid, name) VALUES
                                              (1, 'DRAFT'),
                                              (2, 'ACTIVE'),
                                              (3, 'INACTIVE'),
                                              (4, 'ARCHIVED')
    ON CONFLICT (name) DO NOTHING;


-- For Roles initial data
INSERT INTO role_table (role_id, role) VALUES
                                           (1, 'ADMIN'),
                                           (2, 'USER'),
                                           (3, 'SELLER'),
                                           (4, 'MANAGER'),
                                           (5, 'CUSTOMER_SUPPORT'),
                                           (6, 'DELIVERY_AGENT'),
                                           (7, 'INVENTORY_MANAGER'),
                                           (8, 'FINANCE'),
                                           (9, 'MARKETING'),
                                           (10, 'GUEST')
    ON CONFLICT (role_id) DO NOTHING;
