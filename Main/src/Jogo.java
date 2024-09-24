import java.util.Scanner;

public class Jogo {

    private API api;
    private Scanner scanner;
    private Inventario inventario;

    public Jogo() {
        this.api = new API();
        this.scanner = new Scanner(System.in);
        this.inventario = new Inventario();
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

            // Exibir as opções da cena
            for (int i = 0; i < opcoes.length; i++) {
                System.out.println((i + 1) + ": " + opcoes[i]);
            }

            // Adicionar a opção de "Checar Inventário"
            System.out.println((opcoes.length + 1) + ": Checar Inventário");

            // Se o jogador está na cena 4 e tem a lanterna, adicionar a opção de usar a lanterna
            if (cenaAtual.getProximaCena(0) == 4 && inventario.temItem("Lanterna")) {
                System.out.println((opcoes.length + 2) + ": Usar lanterna");
            }

            System.out.println("Escolha uma opção: ");
            int escolha = scanner.nextInt() - 1;  // Subtrai 1 para alinhar com o índice do array

            // Checar se o jogador escolheu "Checar Inventário"
            if (escolha == opcoes.length) {
                inventario.listarItens();
            } else if (cenaAtual.getProximaCena(0) == 4 && escolha == opcoes.length + 1) {
                // Usar lanterna
                if (inventario.temItem("Lanterna")) {
                    System.out.println("Você usou a lanterna. Agora a casa está iluminada!");

                    // Permitir que o jogador permaneça na cena 4
                    // Atualizar a descrição da cena se necessário
                    cenaAtual = api.carregarCena(4); // Apenas recarregar a cena 4 para mostrar que agora está iluminada
                } else {
                    System.out.println("Você não tem uma lanterna para usar.");
                }
            } else if (escolha < 0 || escolha >= opcoes.length) {
                System.out.println("Escolha inválida. Tente novamente.");
            } else {
                // Carrega a próxima cena com base na escolha
                int proximaCena = cenaAtual.getProximaCena(escolha);
                cenaAtual = api.carregarCena(proximaCena);

                // Lógica de inventário para adicionar itens em cenas específicas
                if (proximaCena == 20) { // Cena onde o jogador encontra a lanterna
                    inventario.adicionarItem("Lanterna");
                } else if (proximaCena == 6) { // Cena onde o jogador encontra o celular
                    inventario.adicionarItem("Celular");
                }
            }
        }
    }
}
