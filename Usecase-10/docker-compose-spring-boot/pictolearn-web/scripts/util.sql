create database Users;

use Users;

CREATE TABLE users (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(80) NOT NULL,
email VARCHAR(50) NOT NULL
);