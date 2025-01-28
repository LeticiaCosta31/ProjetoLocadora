## Descrição Classes

## Classe Filme

* Descrição: Representa um filme na locadora.
* Propriedades:

    * id: Identificador único do filme.
    * titulo: Título do filme.
    * diretor: Diretor do filme.
    * anoLancamento: Ano de lançamento do filme.
    * genero: Gênero do filme.
    * dataEstreia: Data de estreia do filme.

## Classe Locadora

* Descrição: Gerencia as operações de CRUD (Create, Read, Update, Delete) para os filmes.
* Métodos:

    * cadastrarFilme(Filme filme): Cadastra um novo filme.
    * listarFilmes(): Lista todos os filmes cadastrados.
    * buscarFilmePorId(int id): Busca um filme pelo seu ID.
    * atualizarFilme(Filme filme): Atualiza os detalhes de um filme existente.
    * deletarFilme(int id): Deleta um filme pelo seu ID.

## Classe Main

* Descrição: Contém o ponto de entrada da aplicação e gerencia a interação com o usuário.
* Métodos:

    * main(String[] args): Método principal que inicia a aplicação. 
    * listar(Locadora locadora): Lista todos os filmes da locadora.
   
## Classe ValidadorEntrada

* Descrição: Contém métodos para validar diferentes tipos de entrada do usuário.
* Métodos:

    * validarEntradaTexto(Scanner scanner, String mensagem): Valida a entrada de texto, garantindo que não esteja vazia.

    * validarEntradaNumerica(Scanner scanner, String mensagem): Valida a entrada numérica, garantindo que seja um número inteiro.

    * validarEntradaData(Scanner scanner, String mensagem): Valida a entrada de data, garantindo que esteja no formato YYYY-MM-DD e dentro do intervalo permitido (1900-2100).
    
    * validarAnoLancamento(Scanner scanner, String mensagem): Valida o ano de lançamento, garantindo que esteja dentro do intervalo permitido (1900-2100).
   