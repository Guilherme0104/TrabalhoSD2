package entidades.controladores;

import java.io.Serializable;
import java.util.Date;

import entidades.animais.Animal;

public class ConsultaRegistro implements Serializable {
    private Animal animal;
    private Date data;
    private Veterinario veterinario;

    public ConsultaRegistro(Animal animal, Date data, Veterinario veterinario) {
        this.animal = animal;
        this.data = data;
        this.veterinario = veterinario;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Date getData() {
        return data;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    @Override
    public String toString() {
        return "Animal: " + animal + ", Data: " + data + ", Veterinario: " + veterinario;
    }
}
