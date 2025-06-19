package entidades.controladores;

import java.io.Serializable;
import java.util.Date;

import entidades.animais.Animal;

public class Agendamento implements Serializable {
  private Date data;
  private Animal animal;
  private Veterinario veterinario;

  public Agendamento(Date data, Animal animal, Veterinario veterinario) {
    this.data = data;
    this.animal = animal;
    this.veterinario = veterinario;
  }

  public Date getData() {
    return data;
  }

  public Animal getAnimal() {
    return animal;
  }

  public Veterinario getVeterinario() {
    return veterinario;
  }

  
}
