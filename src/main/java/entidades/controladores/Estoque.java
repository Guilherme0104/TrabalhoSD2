package entidades.controladores;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<String, Map<String, Integer>> medicamentosPorAnimal;
    

    public Estoque() {
        this.medicamentosPorAnimal = new HashMap<>();
    }

    public void adicionarItem(String tipoAnimal, String medicamento, int quantidade) {
        medicamentosPorAnimal.putIfAbsent(tipoAnimal, new HashMap<>());
        Map<String, Integer> medicamentos = medicamentosPorAnimal.get(tipoAnimal);
        medicamentos.put(medicamento, medicamentos.getOrDefault(medicamento, 0) + quantidade);
    }

    public void removerItem(String tipoAnimal, String medicamento, int quantidade) {
        if (medicamentosPorAnimal.containsKey(tipoAnimal)) {
            Map<String, Integer> medicamentos = medicamentosPorAnimal.get(tipoAnimal);
            if (medicamentos.containsKey(medicamento)) {
                int atual = medicamentos.get(medicamento);
                medicamentos.put(medicamento, Math.max(0, atual - quantidade));
            }
        }
    }

    public static String stringfy(Map<String, Integer> medicamentos) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : medicamentos.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public Map<String, Integer> getMedicamentos(String tipoAnimal) {
        return medicamentosPorAnimal.get(tipoAnimal);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String tipoAnimal : medicamentosPorAnimal.keySet()) {
            sb.append("Animal: ").append(tipoAnimal).append("\n");
            for (Map.Entry<String, Integer> entry : medicamentosPorAnimal.get(tipoAnimal).entrySet()) {
                sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
        }
        return sb.toString();
    }
}