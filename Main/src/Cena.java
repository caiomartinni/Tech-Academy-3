import java.util.ArrayList;
import java.util.List;

public class Cena {
    private int id;
    private String descricao;
    private List<String> opcoes; // Mudei para List<String>
    private List<Integer> proximasCenas;

    public Cena(int id, String descricao, List<String> opcoes, List<Integer> proximasCenas) {
        this.id = id;
        this.descricao = descricao;
        this.opcoes = opcoes != null ? opcoes : new ArrayList<>(); // Garante que opcoes nunca seja nula
        this.proximasCenas = proximasCenas;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<String> getOpcoes() {
        return opcoes;
    }

    public List<Integer> getProximasCenas() {
        return proximasCenas;
    }

    // Método para obter a próxima cena com base na escolha do jogador
    public int getProximaCena(int index) {
        return proximasCenas.size() > index ? proximasCenas.get(index) : -1; // Retorna -1 se não houver próxima cena
    }
}
