import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class LocadoraTest {
    private Locadora locadora;

    @Before
    public void setUp() throws SQLException {
        locadora = new Locadora();
        // Limpar e recriar a tabela para garantir um ambiente de teste limpo
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Yan\\Desktop\\SQLITE\\locadora.db");
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS filmeMarvel");
            stmt.execute("CREATE TABLE filmeMarvel (" +
                    "id INTEGER NOT NULL," +
                    "titulo VARCHAR(100) NOT NULL," +
                    "diretor VARCHAR(50) NOT NULL," +
                    "ano_lancamento INT NOT NULL," +
                    "genero VARCHAR(50)," +
                    "data_estreia DATE NOT NULL," +
                    "PRIMARY KEY (id)" +
                    ")");
        }
    }

   /*  @After
    public void tearDown() {
        // Limpar a tabela após os testes
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Yan\\Desktop\\SQLITE\\locadora.db");
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS filmeMarvel");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @Test
    public void testCadastrarFilme() {
        Filme filme = new Filme(1, "Homem de Ferro", "Jon Favreau", 2008, "Ação", "2008-05-02");
        locadora.cadastrarFilme(filme);

        List<Filme> filmes = locadora.listarFilmes();
        assertEquals(1, filmes.size());
        assertEquals("Homem de Ferro", filmes.get(0).getTitulo());
    }

    @Test
    public void testListarFilmes() {
        Filme filme1 = new Filme(1, "Homem de Ferro", "Jon Favreau", 2008, "Ação", "2008-05-02");
        Filme filme2 = new Filme(2, "Thor", "Kenneth Branagh", 2011, "Ação", "2011-04-21");
        locadora.cadastrarFilme(filme1);
        locadora.cadastrarFilme(filme2);

        List<Filme> filmes = locadora.listarFilmes();
        assertEquals(2, filmes.size());
    }

    @Test
    public void testBuscarFilmePorId() {
        Filme filme = new Filme(1, "Homem de Ferro", "Jon Favreau", 2008, "Ação", "2008-05-02");
        locadora.cadastrarFilme(filme);

        Filme filmeBuscado = locadora.buscarFilmePorId(1);
        assertNotNull(filmeBuscado);
        assertEquals("Homem de Ferro", filmeBuscado.getTitulo());
    }

    @Test
    public void testAtualizarFilme() {
        Filme filme = new Filme(1, "Homem de Ferro", "Jon Favreau", 2008, "Ação", "2008-05-02");
        locadora.cadastrarFilme(filme);

        Filme filmeAtualizado = new Filme(1, "Homem de Ferro 2", "Jon Favreau", 2010, "Ação", "2010-05-07");
        locadora.atualizarFilme(filmeAtualizado);

        Filme filmeBuscado = locadora.buscarFilmePorId(1);
        assertNotNull(filmeBuscado);
        assertEquals("Homem de Ferro 2", filmeBuscado.getTitulo());
    }

    @Test
    public void testDeletarFilme() {
        Filme filme = new Filme(1, "Homem de Ferro", "Jon Favreau", 2008, "Ação", "2008-05-02");
        locadora.cadastrarFilme(filme);

        locadora.deletarFilme(1);
        Filme filmeBuscado = locadora.buscarFilmePorId(1);
        assertNull(filmeBuscado);
    }
}