public class Cena {
    private String descricao;
    private String[] opcoes;
    private int[] proximasCenas;

    public Cena(String descricao, String[] opcoes, int[] proximasCenas) {
        this.descricao = descricao;
        this.opcoes = opcoes;
        this.proximasCenas = proximasCenas;
    }

    public String getDescricao() {
        return descricao;
    }

    public String[] getOpcoes() {
        return opcoes;
    }

    public int getProximaCena(int escolha) {
        if (escolha >= 0 && escolha < proximasCenas.length) {
            return proximasCenas[escolha];
        }
        return -1;  // Cena inválida
    }
}
