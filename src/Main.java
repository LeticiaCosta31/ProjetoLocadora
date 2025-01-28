
import java.util.List;
import java.util.Scanner;

public class Main {
    
    //Método para listar todos os filmes da locadora.
    private static void listar(Locadora locadora){
        List<Filme> filmes = locadora.listarFilmes();
                for (Filme filme : filmes) {
                    System.out.println(filme);
                }
    }

    public static void main(String[] args) {
        Locadora locadora = new Locadora();
        Scanner scanner = new Scanner(System.in);
        ValidadorEntrada validarEntrada= new ValidadorEntrada();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Listar Filmes");
            System.out.println("2. Buscar Filme por ID");
            System.out.println("3. Cadastrar Filme");
            System.out.println("4. Atualizar Filme");
            System.out.println("5. Deletar Filme");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            if (escolha == 1) {
                listar(locadora);

            } else if (escolha == 2) {
                listar(locadora);
                System.out.println();

                System.out.print("Digite o ID do filme: ");
                int id = scanner.nextInt();
                Filme filme = locadora.buscarFilmePorId(id);
                if (filme != null) {
                    System.out.println(filme);
                } else {
                    System.out.println("Filme não encontrado.");
                }


            } else if (escolha == 3) {
                int id = validarEntrada.validarEntradaNumerica(scanner, "Id: ");
                
              
                String titulo = validarEntrada.validarEntradaTexto(scanner, "Título: ");
                String diretor = validarEntrada.validarEntradaTexto(scanner, "Diretor: ");

                
                int anoLancamento = validarEntrada.validarAnoLancamento(scanner, "Ano de Lançamento: ");

                
                System.out.print("Gênero(opcional): ");
                String genero = scanner.nextLine();

                
                String dataEstreia = validarEntrada.validarEntradaData(scanner, "Data de Estreia (YYYY-MM-DD):");
                

                Filme filme = new Filme(id, titulo, diretor, anoLancamento, genero, dataEstreia);
                locadora.cadastrarFilme(filme);
                System.out.println("Filme cadastrado com sucesso.");
            }

            else if (escolha == 4) {
                listar(locadora);
                System.out.println();

                System.out.print("Digite o ID do filme: ");
                int id = scanner.nextInt();
                scanner.nextLine(); 

                String titulo = validarEntrada.validarEntradaTexto(scanner, "Título: ");
                String diretor = validarEntrada.validarEntradaTexto(scanner, "Diretor: ");

                
                int anoLancamento = validarEntrada.validarAnoLancamento(scanner, "Ano de Lançamento: ");

                System.out.print("Gênero (opcional): ");
                String genero = scanner.nextLine();

                
                String dataEstreia = validarEntrada.validarEntradaData(scanner, "Data de Estreia (YYYY-MM-DD):");

                Filme filme = new Filme(id, titulo, diretor, anoLancamento, genero, dataEstreia);
                locadora.atualizarFilme(filme);
                System.out.println("Filme atualizado com sucesso.");


            } else if (escolha == 5) {
                listar(locadora);
                System.out.print("Digite o ID do filme: ");
                int id = scanner.nextInt();
                locadora.deletarFilme(id);
                
                System.out.println("Filme deletado com sucesso.");

            } else if (escolha == 6) {
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}