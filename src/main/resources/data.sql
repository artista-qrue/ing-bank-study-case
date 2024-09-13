drop table if exists user_roles;
drop table if exists role;
drop table if exists _user;
drop table if exists customer;
drop table if exists asset;
drop table if exists _order;

create table _user
(
    id       bigint not null auto_increment,
    username varchar(255),
    password varchar(255),
    primary key (id)
);

-- password is password
INSERT INTO _user (id, username, password)
VALUES (1, 'admin', '$2a$10$qXQo4z4oXKPEKyYO7bAQmOQ9PhIcHK4LOo/L1U9j/xkLEmseLWECK');

-- password is password
INSERT INTO _user (id, username, password)
VALUES (2, 'Bob', '$2a$10$qXQo4z4oXKPEKyYO7bAQmOQ9PhIcHK4LOo/L1U9j/xkLEmseLWECK');

create table role
(
    id          bigint auto_increment,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);

INSERT INTO role (id, description, name)
VALUES (1, 'Admin role', 'ADMIN');
INSERT INTO role (id, description, name)
VALUES (2, 'User role', 'USER');

create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
);

alter table user_roles
    add constraint fk_role_id foreign key (role_id) references role (id);

alter table user_roles
    add constraint fk_user_id foreign key (user_id) references _user (id);

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id)
VALUES (2, 2);

CREATE TABLE customer
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    iban VARCHAR(34)  NOT NULL UNIQUE
);

CREATE TABLE asset
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT       NOT NULL,
    asset_name  VARCHAR(255) NOT NULL,
    size        DOUBLE       NOT NULL,
    usable_size DOUBLE       NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer (id)
);

CREATE TABLE _order
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT                                  NOT NULL,
    asset_name  VARCHAR(255)                            NOT NULL,
    order_side  ENUM ('BUY', 'SELL')                    NOT NULL,
    size        DOUBLE                                  NOT NULL,
    price       DOUBLE                                  NOT NULL,
    status      ENUM ('PENDING', 'MATCHED', 'CANCELED') NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES Customer (id)
);


-- Insert customer 1
INSERT INTO customer (name, iban)
VALUES ('John Doe', 'TR000000000000000000000001');

-- Insert customer 2
INSERT INTO customer (name, iban)
VALUES ('Jane Smith', 'TR000000000000000000000002');

-- Insert TRY asset for customer 1
INSERT INTO asset (customer_id, asset_name, size, usable_size)
VALUES (1, 'TRY', 10000, 8000);

-- Insert TRY asset for customer 2
INSERT INTO asset (customer_id, asset_name, size, usable_size)
VALUES (2, 'TRY', 5000, 5000);

-- Insert another asset (e.g., AAPL) for customer 1
INSERT INTO asset (customer_id, asset_name, size, usable_size)
VALUES (1, 'AAPL', 100, 100);


-- Insert a BUY order for customer 1
INSERT INTO _order (customer_id, asset_name, order_side, size, price, status)
VALUES (1, 'AAPL', 'BUY', 10, 150, 'PENDING');

-- Insert a SELL order for customer 2
INSERT INTO _order (customer_id, asset_name, order_side, size, price, status)
VALUES (2, 'TRY', 'SELL', 1000, 1, 'PENDING');