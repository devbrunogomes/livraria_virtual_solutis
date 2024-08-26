CREATE DATABASE livraria_virtual;
use livraria_virtual;

CREATE TABLE Livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    autores VARCHAR(255),
    editora VARCHAR(255),
    preco DECIMAL(10, 2),
    tipo ENUM('impresso', 'eletronico'),
    estoque int, 
    tamanho int,
    frete decimal(10 ,2 )
);


CREATE TABLE Venda (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente VARCHAR(255),
    valor DECIMAL(10, 2)
);

CREATE TABLE LivrosVendidos (
    venda_id BIGINT,
    livro_id BIGINT,
    PRIMARY KEY (venda_id, livro_id),
    FOREIGN KEY (venda_id) REFERENCES Venda(id) ON DELETE CASCADE,
    FOREIGN KEY (livro_id) REFERENCES Livro(id) ON DELETE CASCADE
);
