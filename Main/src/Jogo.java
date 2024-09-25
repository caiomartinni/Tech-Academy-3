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

            // Convertendo List<String> para String[]
            String[] opcoes = cenaAtual.getOpcoes().toArray(new String[0]);

            // Se não houver opções, significa que é uma cena final
            if (opcoes.length == 0) {
                System.out.println("Fim de Jogo.");
                break;
            }

            // Exibir as opções da cena
            for (int i = 0; i < opcoes.length; i++) {
                System.out.println((i + 1) + ": " + opcoes[i]);
            }

            // Adicionar opção de checar inventário
            System.out.println((opcoes.length + 1) + ": Checar Inventário");

            // Adicionar a opção de usar a lanterna (se aplicável)
            if (cenaAtual.getProximaCena(0) == 4 && inventario.temItem("Lanterna")) {
                System.out.println((opcoes.length + 2) + ": Usar lanterna");
            }

            // Separar visualmente as opções adicionais
            System.out.println("--------------");
            System.out.println("Comandos:");
            System.out.println("S: Salvar Jogo");
            System.out.println("R: Reiniciar Jogo");
            System.out.println("X: Sair do Jogo");
            System.out.println("--------------");

            System.out.println("Escolha uma opção: ");
            String escolhaInput = scanner.next();  // Usar string para capturar letras

            // Checar inventário
            if (escolhaInput.equalsIgnoreCase(String.valueOf(opcoes.length + 1))) {
                inventario.listarItens();
            }
            // Usar lanterna
            else if (cenaAtual.getProximaCena(0) == 4 && escolhaInput.equalsIgnoreCase(String.valueOf(opcoes.length + 2))) {
                usarLanterna();
            }
            // Salvar jogo
            else if (escolhaInput.equalsIgnoreCase("S")) {
                salvarProgresso();
            }
            // Reiniciar jogo
            else if (escolhaInput.equalsIgnoreCase("R")) {
                System.out.println("Reiniciando o jogo...");
                idCenaAtual = 1;  // Reiniciar a partir da primeira cena
                inventario = new Inventario();  // Limpar o inventário
                jogar();  // Reiniciar o ciclo do jogo
                break;
            }
            // Sair do jogo
            else if (escolhaInput.equalsIgnoreCase("X")) {
                System.out.println("Saindo do jogo...");
                break;
            }
            // Verifica se a escolha é válida para as opções principais
            else {
                try {
                    int escolha = Integer.parseInt(escolhaInput) - 1;

                    if (escolha >= 0 && escolha < opcoes.length) {
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
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida. Por favor, escolha um número ou uma opção adicional válida.");
                }
            }
        }
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

    private void usarLanterna() {
        if (inventario.temItem("Lanterna")) {
            System.out.println("Você usou a lanterna. Agora a casa está iluminada!");
        } else {
            System.out.println("Você não tem uma lanterna para usar.");
        }
    }
}
