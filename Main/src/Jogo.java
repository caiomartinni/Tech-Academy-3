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
        System.out.println("Bem-vindo ao jogo! Digite 'Start' para começar ou 'Carregar' para carregar um jogo salvo.");
        String input = scanner.nextLine();

        if ("Start".equalsIgnoreCase(input)) {
            jogar();
        } else if ("Carregar".equalsIgnoreCase(input) && salvamento.existeProgressoSalvo()) {
            carregarProgresso();
        } else {
            System.out.println("Comando inválido ou sem progresso salvo. Tente novamente.");
        }
    }

    private void jogar() {
        Cena cenaAtual = api.carregarCena(idCenaAtual);

        while (cenaAtual != null) {
            System.out.println(cenaAtual.getDescricao());
            String[] opcoes = cenaAtual.getOpcoes();

            if (opcoes.length == 0) {
                System.out.println("Fim de Jogo.");
                break;
            }

            for (int i = 0; i < opcoes.length; i++) {
                System.out.println((i + 1) + ": " + opcoes[i]);
            }

            // Adicionar opções de salvar jogo e checar inventário
            System.out.println((opcoes.length + 1) + ": Checar Inventário");
            System.out.println((opcoes.length + 2) + ": Salvar Jogo");

            if (cenaAtual.getProximaCena(0) == 4 && inventario.temItem("Lanterna")) {
                System.out.println((opcoes.length + 3) + ": Usar lanterna");
            }

            System.out.println("Escolha uma opção: ");
            int escolha = scanner.nextInt() - 1;

            if (escolha == opcoes.length) {
                inventario.listarItens();
            } else if (escolha == opcoes.length + 1) {
                salvarProgresso();
            } else if (cenaAtual.getProximaCena(0) == 4 && escolha == opcoes.length + 2) {
                usarLanterna();
            } else if (escolha >= 0 && escolha < opcoes.length) {
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
