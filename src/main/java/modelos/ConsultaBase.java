package modelos;

import java.util.ArrayList;
import java.util.Map;

import entidades.animais.Animal;
import entidades.controladores.Agendamento;
import entidades.controladores.Estoque;
import entidades.interfaces.Consulta;

public abstract class ConsultaBase implements Consulta {
    protected Estoque estoque;
    protected ArrayList<Agendamento> agendamentos;
    private ArrayList<Animal> animais;

    public ConsultaBase() {
        this.estoque = new Estoque();
        this.agendamentos = new ArrayList<>();
        this.animais = new ArrayList<>();
    }

    @Override
    public void cancelarConsulta(int id) {
        if (id < agendamentos.size() && id >= 0) {
            agendamentos.remove(id);
        } else {
            throw new IllegalArgumentException("Consulta " + id + " não encontrada.");
        }
    }

    @Override
    public Map<String, Integer> getMedicamentos(String animal) {
        return estoque.getMedicamentos(animal);
    }
    
    @Override
    public void realizarConsulta(Agendamento agendamento) {
        agendamentos.add(agendamento);
    }

    public void aplicarMedicamento(String animal, String medicamento, int quantidade) {
      estoque.removerItem(animal, medicamento, quantidade);
    }

    public void adicionarMedicamento(String animal, String medicamento, int quantidade) {
      estoque.adicionarItem(animal, medicamento, quantidade);
    }

    public void adicionarAnimal(Animal animal) {
        animais.add(animal);
    }

    public ArrayList<Animal> getAnimais() {
        return animais;
    }

    public void removerAnimal(int id) {
        if (id < animais.size() && id >= 0) {
        animais.remove(id);
        } else {
        throw new IllegalArgumentException("Animal " + id + " nao encontrado.");
        }
    }

    public ArrayList<Agendamento> getAgendamentos() {
        return agendamentos;
    }
}