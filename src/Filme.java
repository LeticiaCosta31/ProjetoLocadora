public class Filme {
    private int id;
    private String titulo;
    private String diretor;
    private int anoLancamento;
    private String genero;
    private String dataEstreia;

    public Filme(int id, String titulo, String diretor, int anoLancamento, String genero, String dataEstreia) {
        this.id = id;
        this.titulo = titulo;
        this.diretor = diretor;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.dataEstreia = dataEstreia;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDiretor() { return diretor; }
    public void setDiretor(String diretor) { this.diretor = diretor; }

    public int getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getDataEstreia() { return dataEstreia; }
    public void setDataEstreia(String dataEstreia) { this.dataEstreia = dataEstreia; }

    @Override
    public String toString() {
        return "Filme [id=" + id + ", titulo=" + titulo + ", diretor=" + diretor + ", anoLancamento=" + anoLancamento +
                ", genero=" + genero + ", dataEstreia=" + dataEstreia + "]";
    }
}