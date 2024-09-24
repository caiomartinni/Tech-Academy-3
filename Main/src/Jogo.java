import java.util.Map;
import java.util.Scanner;

public class Jogo {
    private API api;
    private Scanner scanner;
    private Inventario inventario;
    private Salvamento salvamento;
    private int idCenaAtual;

    public Jogo() {
        this.api = new API();
        this.scanner = new Scanner(System.in);
        this.inventario = new Inventario();
        this.salvamento = new Salvamento();
        this.idCenaAtual = 1;  // Começa na primeira cena
    }

    public void start() {
        while (true) {
            System.out.println("Bem-vindo ao jogo! Digite 'Start' para começar, 'Carregar' para carregar um jogo salvo, ou 'Sair' para encerrar.");
            String input = scanner.nextLine();

            if ("Start".equalsIgnoreCase(input)) {
                jogar();  // Iniciar o jogo
            } else if ("Carregar".equalsIgnoreCase(input) && salvamento.existeProgressoSalvo()) {
                carregarProgresso();  // Carregar jogo salvo
            } else if ("Sair".equalsIgnoreCase(input)) {
                System.out.println("Saindo do jogo...");
                break;  // Encerrar o jogo
            } else {
                System.out.println("Comando inválido ou sem progresso salvo. Tente novamente.");
            }
        }
    }

    private void jogar() {
        Cena cenaAtual = api.carregarCena(idCenaAtual);

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

            // Adicionar opções de salvar jogo, checar inventário, reiniciar e sair
            System.out.println((opcoes.length + 1) + ": Checar Inventário");
            System.out.println((opcoes.length + 2) + ": Salvar Jogo");
            System.out.println((opcoes.length + 3) + ": Reiniciar Jogo");
            System.out.println((opcoes.length + 4) + ": Sair do Jogo");

            if (cenaAtual.getProximaCena(0) == 4 && inventario.temItem("Lanterna")) {
                System.out.println((opcoes.length + 5) + ": Usar lanterna");
            }

            System.out.println("Escolha uma opção: ");
            int escolha = scanner.nextInt() - 1;

            // Checar inventário
            if (escolha == opcoes.length) {
                inventario.listarItens();
            }
            // Salvar jogo
            else if (escolha == opcoes.length + 1) {
                salvarProgresso();
            }
            // Reiniciar jogo
            else if (escolha == opcoes.length + 2) {
                System.out.println("Reiniciando o jogo...");
                idCenaAtual = 1;  // Reiniciar a partir da primeira cena
                inventario = new Inventario();  // Limpar o inventário
                jogar();  // Reiniciar o ciclo do jogo
                break;
            }
            // Sair do jogo
            else if (escolha == opcoes.length + 3) {
                System.out.println("Saindo do jogo...");
                break;
            }
            // Usar lanterna
            else if (cenaAtual.getProximaCena(0) == 4 && escolha == opcoes.length + 4) {
                usarLanterna();
            }
            // Verifica se a escolha é válida
            else if (escolha >= 0 && escolha < opcoes.length) {
                idCenaAtual = cenaAtual.getProximaCena(escolha);
                cenaAtual = api.carregarCena(idCenaAtual);

                if (idCenaAtual == 20) {
                    inventario.adicionarItem("Lanterna");
                } else if (idCenaAtual == 6) {
                    inventario.adicionarItem("Celular");
                }
            } else {
                System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }

    private void usarLanterna() {
    }

    private void salvarProgresso() {
        salvamento.salvarProgresso(idCenaAtual, inventario);
    }

    private void carregarProgresso() {
        Map<String, Object> progresso = salvamento.carregarProgresso();
        if (progresso != null) {
            this.idCenaAtual = (int) progresso.get("cenaAtual");
            this.inventario = (Inventario) progresso.get("inventario");
            System.out.println("Progresso carregado com sucesso!");
            jogar();
        }
    }
}
