
CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE
);

INSERT INTO category (name, is_active)
VALUES ('Electronics', true);

-- SUB_CATEGORY TABLE --

CREATE TABLE sub_category (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    category_id INT,
    name VARCHAR(50) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

INSERT INTO sub_category (name, is_active, category_id)
VALUES
	('Monitors', true, 1),
	('Camera', true, 1);

-- PRODUCT TABLE --

CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    sub_category_id INT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(2000),
    price DECIMAL(10, 2) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (sub_category_id) REFERENCES sub_category(id)
);

INSERT INTO product (sub_category_id, name, description, price)
VALUES
    (1, 'SAMSUNG', 'High-end monitors with advanced features.', 7999.00),
    (1, 'DELL', 'Powerful monitors for productivity and gaming.', 11499.99),
    (2, 'Canon', 'High-end Camera with advanced features.', 70099.99),
    (2, 'Sony', 'Powerful camera for productivity and more.', 114499.99);
    
	SELECT * FROM category;
    SELECT * FROM sub_category;
	SELECT * FROM product;