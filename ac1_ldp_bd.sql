DROP DATABASE IF EXISTS clientes;
CREATE DATABASE clientes;
USE clientes;

CREATE TABLE cliente (
	CPF				VARCHAR(75)		primary key		not null,		
	Nome			VARCHAR(100)	not null,
	Telefone		VARCHAR(45)		not null,
    Cidade			VARCHAR(100)	not null,
    UF				VARCHAR(30)		not null,
    Email			VARCHAR(75)		not null
)