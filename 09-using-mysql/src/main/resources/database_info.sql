CREATE DATABASE using_mysql;
USE using_mysql;

CREATE TABLE client (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    cpf VARCHAR(11)
);

CREATE TABLE product (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(100),
    unit_price NUMERIC(20,2)
);

CREATE TABLE purchase_order (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    client_id INTEGER REFERENCES client (id),
    order_date TIMESTAMP,
    status VARCHAR(100),
    total NUMERIC(20,2)
);

CREATE TABLE item_order (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    order_id INTEGER REFERENCES purchase_order (id),
    product_id INTEGER REFERENCES product (id),
    amount INTEGER
);

CREATE TABLE user (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    is_admin BOOL DEFAULT FALSE
);