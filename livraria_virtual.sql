CREATE DATABASE livraria_virtual;
use livraria_virtual;

CREATE TABLE Livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    autores VARCHAR(255),
    editora VARCHAR(255),
    preco DECIMAL(10, 2),
    tipo ENUM('LivroImpresso', 'LivroEletronico')
);

CREATE TABLE LivroImpresso (
    id BIGINT PRIMARY KEY,
    frete DECIMAL(10, 2),
    estoque INT,
    FOREIGN KEY (id) REFERENCES Livro(id)
);

CREATE TABLE LivroEletronico (
    id BIGINT PRIMARY KEY,
    tamanho INT,
    FOREIGN KEY (id) REFERENCES Livro(id)
);

CREATE TABLE Venda (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente VARCHAR(255),
    valor DECIMAL(10, 2)
);

CREATE TABLE LivroVenda (
    venda_id BIGINT,
    livro_id BIGINT,
    FOREIGN KEY (venda_id) REFERENCES Venda(id),
    FOREIGN KEY (livro_id) REFERENCES Livro(id),
    PRIMARY KEY (venda_id, livro_id) 
);

SELECT * FROM Livro WHERE Editora = aaa;