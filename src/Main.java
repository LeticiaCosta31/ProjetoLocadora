import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Método genérico para validar entrada de texto.
     * Solicita repetidamente até que uma entrada válida seja fornecida.
     * 
     * @param scanner  Scanner para capturar entrada do usuário.
     * @param mensagem Mensagem para solicitar a entrada.
     * @return A entrada validada como String.
     */
    private static String validarEntradaTexto(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        String entrada = scanner.nextLine();
        while (entrada.trim().isEmpty()) { // Verifica se está vazia ou contém apenas espaços
            System.out.println("O campo é obrigatório. Por favor, insira um valor válido.");
            System.out.print(mensagem);
            entrada = scanner.nextLine();
        }
        return entrada;
    }

    /**
     * Método genérico para validar entrada numérica (int).
     * Solicita repetidamente até que uma entrada válida seja fornecida.
     * 
     * @param scanner  Scanner para capturar entrada do usuário.
     * @param mensagem Mensagem para solicitar a entrada.
     * @return A entrada validada como int.
     */
    private static int validarEntradaNumerica(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("A entrada deve ser um número. Por favor, tente novamente.");
            System.out.print(mensagem);
            scanner.next(); // Descarta a entrada inválida
        }
        int entrada = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha
        return entrada;
    }

    /*
     * Método para listar todos os filmes da locadora.
     * 
     */
    private static void listar(Locadora locadora){
        List<Filme> filmes = locadora.listarFilmes();
                for (Filme filme : filmes) {
                    System.out.println(filme);
                }
    }

    public static void main(String[] args) {
        Locadora locadora = new Locadora();
        Scanner scanner = new Scanner(System.in);

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
                int id = validarEntradaNumerica(scanner, "Id: ");

                // Usa o método genérico para validar entradas de texto
                String titulo = validarEntradaTexto(scanner, "Título: ");
                String diretor = validarEntradaTexto(scanner, "Diretor: ");

                // Usa o método genérico para validar entrada numérica
                int anoLancamento = validarEntradaNumerica(scanner, "Ano de Lançamento: ");

                // Gênero é opcional, então não exige validação
                System.out.print("Gênero (opcional): ");
                String genero = scanner.nextLine();

                // Validação da data de estreia
                String dataEstreia = validarEntradaTexto(scanner, "Data de Estreia (YYYY-MM-DD): ");

                // Cria o filme após validação
                Filme filme = new Filme(id, titulo, diretor, anoLancamento, genero, dataEstreia);
                locadora.cadastrarFilme(filme);
                System.out.println("Filme cadastrado com sucesso.");
            }

            else if (escolha == 4) {
                listar(locadora);
                System.out.println();
                System.out.print("Digite o ID do filme: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                System.out.print("Título: ");
                String titulo = scanner.nextLine();

                System.out.print("Diretor: ");
                String diretor = scanner.nextLine();

                System.out.print("Ano de Lançamento: ");
                int anoLancamento = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                System.out.print("Gênero: ");
                String genero = scanner.nextLine();

                System.out.print("Data de Estreia (YYYY-MM-DD): ");
                String dataEstreia = scanner.nextLine();

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