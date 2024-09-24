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

    public void adicionarOpcao(String novaOpcao) {
        String[] novasOpcoes = new String[opcoes.length + 1];
        int[] novasProximasCenas = new int[proximasCenas.length + 1];

        System.arraycopy(opcoes, 0, novasOpcoes, 0, opcoes.length);
        System.arraycopy(proximasCenas, 0, novasProximasCenas, 0, proximasCenas.length);

        novasOpcoes[opcoes.length] = novaOpcao;
        novasProximasCenas[proximasCenas.length] = -1; // Usar lanterna não leva a nova cena.

        this.opcoes = novasOpcoes;
        this.proximasCenas = novasProximasCenas;
    }
}
