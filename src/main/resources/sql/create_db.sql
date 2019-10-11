CREATE DATABASE car_workshop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE employee
(
    id            int AUTO_INCREMENT,
    name          VARCHAR(20),
    lastname      VARCHAR(20),
    address       VARCHAR(255),
    phone_number  VARCHAR(20),
    note          VARCHAR(255),
    cost_per_hour DOUBLE,
    password      VARCHAR(255),
    token         VARCHAR(255),
    email         VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE customer
(
    id            int AUTO_INCREMENT,
    name          VARCHAR(20),
    lastname      VARCHAR(20),
    date_of_birth DATE,
    PRIMARY KEY (id)
);

CREATE TABLE vehicle
(
    id                        int AUTO_INCREMENT,
    customer_id               int,
    model                     VARCHAR(20),
    brand                     VARCHAR(20),
    year_of_production        int,
    registration_number       VARCHAR(10),
    next_technical_inspection DATE,
    PRIMARY KEY (id)
);

CREATE TABLE status
(

    id   int AUTO_INCREMENT,
    name VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE customer_order
(
    id                  int AUTO_INCREMENT,
    customer_id         int,
    vehicle_id          int,
    status_id           int,
    date_order_accepted DATE,
    date_repair_start   DATE,
    problem_description TEXT,
    repair_description  TEXT,
    cost_repair         DOUBLE,
    cost_parts          DOUBLE,
    cost_per_hour       DOUBLE,
    number_of_man_hours int,
    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (customer_id) REFERENCES customer (id),
    CONSTRAINT FOREIGN KEY (vehicle_id) REFERENCES vehicle (id),
    CONSTRAINT FOREIGN KEY (status_id) REFERENCES status (id)
);

## ----------------------------

