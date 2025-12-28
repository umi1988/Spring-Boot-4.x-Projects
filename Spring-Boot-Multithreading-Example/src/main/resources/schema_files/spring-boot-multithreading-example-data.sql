-- Schema (no quotes around column names)

CREATE TABLE sb_multithreading_schema.category (
    id bigint PRIMARY KEY,
    name varchar(255),
    type varchar(255),
    status varchar(255)
);

CREATE TABLE sb_multithreading_schema.products (
    id bigint PRIMARY KEY,
    category_id bigint,
    name varchar(255),
    description text,
    status varchar(255),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE sb_multithreading_schema.price (
    id bigint PRIMARY KEY,
    product_id bigint,
    price double,
    valid_from timestamp,
    valid_to timestamp,
    status varchar(255),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE sb_multithreading_schema.inventory (
    id bigint PRIMARY KEY,
    product_id bigint,
    warehouse_id bigint,
    available_quantity integer,
    reserved_quantity integer,
    status varchar(255),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Insert data (no quotes around status)
INSERT INTO sb_multithreading_schema.category (id, name, type, status) VALUES
(1, 'Electronics', 'Goods', 'active'),
(2, 'Clothing', 'Goods', 'active'),
(3, 'Home Appliances', 'Goods', 'active');

INSERT INTO sb_multithreading_schema.products (id, category_id, name, description, status) VALUES
(1, 1, 'Smartphone', 'Latest smartphone with high-speed performance', 'active'),
(2, 1, 'Laptop', '15-inch laptop with 8GB RAM, 256GB SSD', 'active'),
(3, 2, 'Jacket', 'Waterproof jacket for outdoor activities', 'active'),
(4, 3, 'Air Conditioner', '1.5 ton AC with energy-efficient cooling', 'active');

INSERT INTO sb_multithreading_schema.price (id, product_id, price, valid_from, valid_to, status) VALUES
(1, 1, 699.99, NOW(), DATE_ADD(NOW(), INTERVAL 1 YEAR), 'active'),
(2, 2, 1299.99, NOW(), DATE_ADD(NOW(), INTERVAL 1 YEAR), 'active'),
(3, 3, 59.99, NOW(), DATE_ADD(NOW(), INTERVAL 1 YEAR), 'active'),
(4, 4, 299.99, NOW(), DATE_ADD(NOW(), INTERVAL 1 YEAR), 'active');

INSERT INTO sb_multithreading_schema.inventory (id, product_id, warehouse_id, available_quantity, reserved_quantity, status) VALUES
(1, 1, 1, 100, 10, 'available'),
(2, 2, 1, 50, 5, 'available'),
(3, 3, 2, 200, 20, 'available'),
(4, 4, 3, 30, 2, 'available');

-- Verify
--SELECT * FROM sb_multithreading_schema.category;
--SELECT * FROM sb_multithreading_schema.products;
--SELECT * FROM sb_multithreading_schema.price;
--SELECT * FROM sb_multithreading_schema.inventory;
