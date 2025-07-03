package modelos;

import java.util.ArrayList;
import java.util.List;

import entidades.controladores.Agendamento;
import entidades.controladores.Veterinario;


public class ConsultaVeterinaria extends ConsultaBase {
  private ArrayList<Veterinario> veterinarios;

  public ConsultaVeterinaria() {
    super();
    this.veterinarios = new ArrayList<>();
  }

  public ConsultaVeterinaria(List<Agendamento> historico) {
    super();
    this.veterinarios = new ArrayList<>();
    if (historico != null && !historico.isEmpty()) {
      for (Agendamento registro : historico) {
          this.agendamentos.add(new Agendamento(registro.getData(), registro.getAnimal(), registro.getVeterinario()));
      }
    }
  }

  public ArrayList<Veterinario> getVeterinarios() {
    return veterinarios;
  }

  public void adicionarVeterinario(Veterinario veterinario) {
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