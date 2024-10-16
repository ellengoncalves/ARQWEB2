-- DROP DATABASE techcare;
CREATE DATABASE techcare;
USE techcare;

SELECT * FROM techcare.user;

CREATE TABLE user (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(150) NOT NULL,
    telefone varchar(11),
    cpf VARCHAR(11) NOT NULL,
      active BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
