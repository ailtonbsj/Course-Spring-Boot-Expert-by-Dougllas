CREATE TABLE CLIENT (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100)
);

CREATE TABLE PRODUCT (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    DESCRIPTION VARCHAR(100),
    UNIT_PRICE NUMERIC(20,2)
);

CREATE TABLE PURCHASE_ORDER (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENT_ID INTEGER REFERENCES CLIENT (ID),
    ORDER_DATE TIMESTAMP,
    AMOUNT NUMERIC(20,2)
);

CREATE TABLE ITEM_ORDER (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    ORDER_ID INTEGER REFERENCES PURCHASE_ORDER (ID),
    PRODUCT_ID INTEGER REFERENCES PRODUCT(ID),
    TOTAL INTEGER
);
