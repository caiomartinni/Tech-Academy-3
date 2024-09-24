import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<String> itens;

    public Inventario() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(String item) {
        if (!itens.contains(item)) {
            itens.add(item);
            System.out.println(item + " foi adicionado ao seu inventário.");
        } else {
            System.out.println("Você já tem " + item + " no seu inventário.");
        }
    }

    public void removerItem(String item) {
        if (itens.contains(item)) {
            itens.remove(item);
            System.out.println(item + " foi removido do seu inventário.");
        } else {
            System.out.println(item + " não está no seu inventário.");
        }
    }

    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("O inventário está vazio!");
        } else {
            System.out.println("Itens no seu inventário: " + itens);
        }
    }

    public boolean temItem(String item) {
        return itens.contains(item);
    }
}
