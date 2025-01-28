## Script usado para a criação da tabela no SQLite

CREATE TABLE filmesMarvel (
    id INTEGER NOT NULL,               
    titulo VARCHAR(100) NOT NULL,
    diretor VARCHAR(50) NOT NULL,
    ano_lancamento INT NOT NULL,
    genero VARCHAR(50),
    data_estreia DATE NOT NULL,
    PRIMARY KEY (id)                 
);