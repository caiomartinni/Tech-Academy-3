import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Salvamento {
    private static final String FILE_NAME = "progresso_salvo.dat";

    public void salvarProgresso(int idCenaAtual, Inventario inventario) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            Map<String, Object> progresso = new HashMap<>();
            progresso.put("cenaAtual", idCenaAtual);
            progresso.put("inventario", inventario);
            out.writeObject(progresso);
            System.out.println("Progresso salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o progresso: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> carregarProgresso() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Map<String, Object>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o progresso: " + e.getMessage());
            return null;
        }
    }

    public boolean existeProgressoSalvo() {
        File file = new File(FILE_NAME);
        return file.exists();
    }
}
