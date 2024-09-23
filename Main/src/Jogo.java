import java.util.Scanner;

public class Jogo {
    private API api;
    private Scanner scanner;

    public Jogo() {
        this.api = new API();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Bem-vindo ao jogo! Digite 'Start' para começar.");
        String input = scanner.nextLine();

        if ("Start".equalsIgnoreCase(input)) {
            jogar();
        } else {
            System.out.println("Comando inválido. Tente novamente.");
        }
    }

    private void jogar() {
        Cena cenaAtual = api.carregarCena(1);

        while (cenaAtual != null) {
            System.out.println(cenaAtual.getDescricao());
            String[] opcoes = cenaAtual.getOpcoes();

            // Se não houver opções, significa que é uma cena final
            if (opcoes.length == 0) {
                System.out.println("Fim de Jogo.");
                break;
            }

            for (int i = 0; i < opcoes.length; i++) {
                System.out.println((i + 1) + ": " + opcoes[i]);
            }

            System.out.println("Escolha uma opção: ");
            int escolha = scanner.nextInt() - 1;  // Subtrai 1 para alinhar com o índice do array

            // Checa se a escolha é válida
            if (escolha < 0 || escolha >= opcoes.length) {
                System.out.println("Escolha inválida. Tente novamente.");
            } else {
                // Carrega a próxima cena com base na escolha
                int proximaCena = cenaAtual.getProximaCena(escolha);
                cenaAtual = api.carregarCena(proximaCena);
            }
        }
    }
}
