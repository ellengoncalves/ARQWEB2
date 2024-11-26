/* DROP DATABASE techcare; */
CREATE DATABASE techcare;
USE techcare;

DROP TABLE client;

/* SELECT * FROM techcare.client; */

CREATE TABLE cliente (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefone VARCHAR(11),
    cpf VARCHAR(11) NOT NULL,
    ativo BOOLEAN NOT NULL;
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE endereco {
    logradouro VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(50),
    bairro VARCHAR(50) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(20) NOT NULL;
} ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE activity (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	type VARCHAR(20) NOT NULL,
	activity_date DATE NOT NULL,
	distance DOUBLE NOT NULL,
	duration INT NOT NULL,
	user_id BIGINT(20) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*select * from techcare.ordem_servico;*/

CREATE TABLE ordem_servico (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(150) NOT NULL,
	status VARCHAR(20) NOT NULL,
	data_emissao DATE NOT NULL,
	data_finalizacao DATE NOT NULL,
	valor DECIMAL(10, 2) NOT NULL,
	observacao VARCHAR(100),
	codigo_cliente BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_cliente) REFERENCES client(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


