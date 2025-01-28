import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// Classe principal da Locadora
public class Locadora {

    // Instância do logger para registrar mensagens de log
    private static final Logger logger = Logger.getLogger(Locadora.class.getName());

    // Bloco estático para configurar o sistema de logs
    static {
        try {
            // Reinicia as configurações padrão do LogManager
            LogManager.getLogManager().reset();
            // Configura um FileHandler para salvar os logs em um arquivo
            FileHandler fileHandler = new FileHandler("locadora.log", true); // "true" habilita append ao arquivo
            // Define o formato do log para ser simples
            fileHandler.setFormatter(new SimpleFormatter());
            // Adiciona o FileHandler ao logger
            logger.addHandler(fileHandler);
            // Define o nível do logger para registrar todas as mensagens (INFO, WARNING, SEVERE)
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            // Exibe mensagem no console caso ocorra erro na configuração dos logs
            System.out.println("Erro ao configurar o sistema de logs: " + e.getMessage());
        }
    }

    // Método para estabelecer uma conexão com o banco de dados SQLite
    private Connection connect() {
        // URL para o banco de dados SQLite
        String url = "jdbc:sqlite:C:\\Users\\Yan\\Desktop\\SQLITE\\locadora.db";
        Connection conn = null;

        try {
            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(url);
            // Registra no log que a conexão foi estabelecida
            logger.info("Conexão com o banco de dados estabelecida.");
        } catch (SQLException e) {
            // Registra no log um erro caso a conexão falhe
            logger.log(Level.SEVERE, "Erro ao conectar ao banco de dados.", e);
        }
        return conn; // Retorna a conexão (ou null, caso ocorra erro)
    }

    // Método para cadastrar um novo filme no banco de dados
    public void cadastrarFilme(Filme filme) {
        // Comando SQL para inserir um novo filme na tabela
        String sql = "INSERT INTO filmeMarvel(id, titulo, diretor, ano_lancamento, genero, data_estreia) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connect(); // Estabelece a conexão com o banco
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara o comando SQL

            // Define os parâmetros do comando SQL
            pstmt.setInt(1, filme.getId());
            pstmt.setString(2, filme.getTitulo());
            pstmt.setString(3, filme.getDiretor());
            pstmt.setInt(4, filme.getAnoLancamento());
            pstmt.setString(5, filme.getGenero());
            pstmt.setString(6, filme.getDataEstreia());
            pstmt.executeUpdate(); // Executa o comando SQL
            // Registra no log que o filme foi cadastrado com sucesso
            logger.info("Filme cadastrado com sucesso: " + filme.getTitulo());
        } catch (SQLException e) {
            // Registra no log um erro caso o cadastro falhe
            logger.log(Level.SEVERE, "Erro ao cadastrar filme: " + filme.getTitulo(), e);
        }
    }

    // Método para listar todos os filmes cadastrados
    public List<Filme> listarFilmes() {
        // Comando SQL para buscar todos os registros da tabela
        String sql = "SELECT * FROM filmeMarvel";
        List<Filme> filmes = new ArrayList<>(); // Lista para armazenar os filmes encontrados

        try (Connection conn = this.connect(); // Estabelece a conexão
             Statement stmt = conn.createStatement(); // Cria um Statement para executar o comando SQL
             ResultSet rs = stmt.executeQuery(sql)) { // Executa o comando SQL e obtém os resultados

            // Itera sobre os registros retornados pelo banco de dados
            while (rs.next()) {
                Filme filme = new Filme(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("diretor"),
                        rs.getInt("ano_lancamento"),
                        rs.getString("genero"),
                        rs.getString("data_estreia")
                );
                filmes.add(filme); // Adiciona o filme à lista
            }
            // Registra no log o número total de filmes listados
            logger.info("Filmes listados com sucesso. Total: " + filmes.size());

            // Registra um aviso no log caso nenhum filme seja encontrado
            if (filmes.isEmpty()) {
                logger.warning("Nenhum filme foi encontrado no banco de dados.");
            }
        } catch (SQLException e) {
            // Registra no log um erro caso a listagem falhe
            logger.log(Level.SEVERE, "Erro ao listar filmes.", e);
        }
        return filmes; // Retorna a lista de filmes
    }

    // Método para buscar um filme pelo seu ID
    public Filme buscarFilmePorId(int id) {
        // Comando SQL para buscar um filme pelo ID
        String sql = "SELECT * FROM filmeMarvel WHERE id = ?";
        Filme filme = null; // Inicializa como null caso nenhum filme seja encontrado

        try (Connection conn = this.connect(); // Estabelece a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara o comando SQL

            pstmt.setInt(1, id); // Define o ID como parâmetro
            ResultSet rs = pstmt.executeQuery(); // Executa a consulta

            // Caso o filme seja encontrado, inicializa o objeto Filme
            if (rs.next()) {
                filme = new Filme(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("diretor"),
                        rs.getInt("ano_lancamento"),
                        rs.getString("genero"),
                        rs.getString("data_estreia")
                );
                // Registra no log que o filme foi encontrado
                logger.info("Filme encontrado: " + filme.getTitulo());
            } else {
                // Registra um aviso no log caso o filme não seja encontrado
                logger.warning("Filme com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            // Registra no log um erro caso a busca falhe
            logger.log(Level.SEVERE, "Erro ao buscar filme com ID: " + id, e);
        }
        return filme; // Retorna o filme encontrado (ou null)
    }

    // Método para atualizar as informações de um filme
    public void atualizarFilme(Filme filme) {
        // Comando SQL para atualizar um filme pelo ID
        String sql = "UPDATE filmeMarvel SET titulo = ?, diretor = ?, ano_lancamento = ?, genero = ?, data_estreia = ? WHERE id = ?";

        try (Connection conn = this.connect(); // Estabelece a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara o comando SQL

            // Define os valores a serem atualizados
            pstmt.setString(1, filme.getTitulo());
            pstmt.setString(2, filme.getDiretor());
            pstmt.setInt(3, filme.getAnoLancamento());
            pstmt.setString(4, filme.getGenero());
            pstmt.setString(5, filme.getDataEstreia());
            pstmt.setInt(6, filme.getId());
            pstmt.executeUpdate(); // Executa a atualização
            // Registra no log que o filme foi atualizado com sucesso
            logger.info("Filme atualizado com sucesso: " + filme.getTitulo());
        } catch (SQLException e) {
            // Registra no log um erro caso a atualização falhe
            logger.log(Level.SEVERE, "Erro ao atualizar filme: " + filme.getTitulo(), e);
        }
    }

    // Método para deletar um filme pelo seu ID
    public void deletarFilme(int id) {
        // Comando SQL para deletar um filme pelo ID
        String sql = "DELETE FROM filmeMarvel WHERE id = ?";

        try (Connection conn = this.connect(); // Estabelece a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql)) { //Prepara o comando SQL

            pstmt.setInt(1, id); // Define o ID como parâmetro
            pstmt.executeUpdate(); // Executa o comando de exclusão
            // Registra no log que o filme foi deletado
            logger.info("Filme deletado com sucesso. ID: " + id);
        } catch (SQLException e) {
            // Registra no log um erro caso a exclusão falhe
            logger.log(Level.SEVERE, "Erro ao deletar filme com ID: " + id, e);
        }
    }
}
