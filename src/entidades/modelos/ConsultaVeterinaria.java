package entidades.modelos;

import java.util.ArrayList;
import java.util.List;

import entidades.dtos.ConsultaRegistro;

public class ConsultaVeterinaria extends ConsultaBase {
  private ArrayList<String> veterinarios;

  public ConsultaVeterinaria() {
    super();
    this.veterinarios = new ArrayList<>();
  }

  public ConsultaVeterinaria(List<ConsultaRegistro> historico) {
    super();
    this.veterinarios = new ArrayList<>();
    if (historico != null && !historico.isEmpty()) {
      for (ConsultaRegistro registro : historico) {
          this.agendamentos.add(registro.getData());
      }
    }
  }

  public ArrayList<String> getVeterinarios() {
    return veterinarios;
  }

  public void adicionarVeterinario(String veterinario) {
    veterinarios.add(veterinario);
  }

  public void removerVeterinario(int id) {
    if (id < veterinarios.size() && id >= 0) {
      veterinarios.remove(id);
    } else {
      throw new IllegalArgumentException("Veterinario " + id + " nao encontrado.");
    }
  }

  @Override
  public String toString() {
      return "Tipo: VETERINARIA\nVeterinarios: " + veterinarios + "\nAgendamentos: " + agendamentos;
  }

}