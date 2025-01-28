import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class ValidadorEntrada {

    /**
     * Método para validar entrada de texto.
     * Solicita repetidamente até que uma entrada válida seja fornecida.
     * 
     * @param scanner  Scanner para capturar entrada do usuário.
     * @param mensagem Mensagem para solicitar a entrada.
     * @return A entrada validada como String.
     */
    public String validarEntradaTexto(Scanner scanner, String mensagem) {
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
     * Método para validar entrada numérica (int).
     * Solicita repetidamente até que uma entrada válida seja fornecida.
     * 
     * @param scanner  Scanner para capturar entrada do usuário.
     * @param mensagem Mensagem para solicitar a entrada.
     * @return A entrada validada como int.
     */
    public int validarEntradaNumerica(Scanner scanner, String mensagem) {
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

    /**
     * Método para validar a data de estreia.
     * Solicita repetidamente até que uma data válida seja fornecida.
     * 
     * @param scanner  Scanner para capturar entrada do usuário.
     * @param mensagem Mensagem para solicitar a entrada.
     * @return A data validada como String no formato YYYY-MM-DD.
     */
    public String validarEntradaData(Scanner scanner, String mensagem) {
        String entrada;
        while (true) {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("O campo é obrigatório. Por favor, insira um valor válido.");
                continue;
            }

            String[] vet = entrada.split("-");
            if (vet.length != 3) {
                System.out.println("Formato de data inválido. Por favor, insira uma data no formato YYYY-MM-DD.");
                continue;
            }

            try {
                int ano = Integer.parseInt(vet[0]);
                int mes = Integer.parseInt(vet[1]);
                int dia = Integer.parseInt(vet[2]);

                if (ano < 1900 || ano > 2100) {
                    System.out.println("Ano fora do intervalo permitido (1900-2100). Por favor, insira uma data válida.");
                    continue;
                }

                if (mes < 1 || mes > 12) {
                    System.out.println("Mês inválido. Por favor, insira um mês entre 01 e 12.");
                    continue;
                }

                if (dia < 1 || dia > 31) {
                    System.out.println("Dia inválido. Por favor, insira um dia entre 01 e 31.");
                    continue;
                }

                // Verifica se o dia é válido para o mês e ano fornecidos
                if (!isValidDate(ano, mes, dia)) {
                    System.out.println("Data inválida. Por favor, insira uma data real.");
                    continue;
                }

                break; // Data válida, sai do loop

            } catch (NumberFormatException e) {
                System.out.println("Formato de data inválido. Por favor, insira uma data no formato YYYY-MM-DD.");
            }
        }
        return entrada;
    }

    /**
     * Método auxiliar para verificar se a data é válida.
     * 
     * @param ano Ano da data.
     * @param mes Mês da data.
     * @param dia Dia da data.
     * @return true se a data for válida, false caso contrário.
     */
    private static boolean isValidDate(int ano, int mes, int dia) {
        try {
            LocalDate date = LocalDate.of(ano, mes, dia);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Método para validar o ano de lançamento.
     * Solicita repetidamente até que um ano válido seja fornecido.
     * 
     * @param scanner  Scanner para capturar entrada do usuário.
     * @param mensagem Mensagem para solicitar a entrada.
     * @return O ano validado como int.
     */
    public int validarAnoLancamento(Scanner scanner, String mensagem) {
        int ano;
        while (true) {
            ano = validarEntradaNumerica(scanner, mensagem);
            if (ano < 1900 || ano > 2100) {
                System.out.println("Ano fora do intervalo permitido (1900-2100). Por favor, insira um ano válido.");
            } else {
                break; // Ano válido, sai do loop
            }
        }
        return ano;
    }

}