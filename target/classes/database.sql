-- Table: users
CREATE TABLE users
(
    id       INT         NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    age      INT          NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;

-- Table for mapping users and roles: users_roles
CREATE TABLE users_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
)
    ENGINE = InnoDB;

-- Insert data

INSERT INTO users
VALUES (1, 'admin', 35, '$2y$10$tzu/ddg7ATXLaaurZiWB8.0DMVHPBitaBo1PDkOuINgNqW1kPQmF6');

INSERT INTO roles
VALUES (1, 'ROLE_USER');
INSERT INTO roles
VALUES (2, 'ROLE_ADMIN');

INSERT INTO users_roles
VALUES (1, 2);
VALUES (1, 1);