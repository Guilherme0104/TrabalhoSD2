package modelos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;

import entidades.animais.Animal;
import entidades.controladores.Agendamento;
import entidades.interfaces.Consulta;

public class ConsultaServico extends UnicastRemoteObject implements Consulta {
    private final ConsultaBase consulta;

    public ConsultaServico() throws RemoteException {
       super();

       this.consulta = new ConsultaVeterinaria();
    }

    @Override
    public void realizarConsulta(Agendamento agendamento) throws RemoteException {
        consulta.realizarConsulta(agendamento);
        System.out.println("Nova consulta agendada para:" + agendamento);
    }

    @Override
    public void cancelarConsulta(int id) throws RemoteException {
        try {
            consulta.cancelarConsulta(id);
            System.out.println("Consulta " + id + " cancelada.");
        } catch (IllegalArgumentException e) {
            System.err.println("erro ao cancelar consulta:" + e.getMessage());
            throw new RemoteException("erro ao cancelar consulta:", e);
        }
        
    }

    @Override
    public Map<String,Integer> getMedicamentos(String animal) throws RemoteException {
        System.out.println("Buscando medicamento para o animal: "+ animal);
        return consulta.getMedicamentos(animal);
    }

    @Override
    public void adicionarAnimal(Animal animal) throws RemoteException {
        this.consulta.adicionarAnimal(animal);
    }

    @Override
    public void adicionarMedicamento(String animal, String medicamento, int quantidade) throws RemoteException {
        this.consulta.adicionarMedicamento(animal, medicamento, quantidade);
    }

    @Override
    public void aplicarMedicamento(String animal, String medicamento, int quantidade) throws RemoteException {
        this.aplicarMedicamento(animal, medicamento, quantidade);
    }

    @Override
    public ArrayList<Animal> getAnimais() throws RemoteException {
        return this.consulta.getAnimais();
    }

    @Override
    public void removerAnimal(int id) throws RemoteException {
        this.consulta.removerAnimal(id);
    }

    @Override
    public ArrayList<Agendamento> getAgendamentos() throws RemoteException {
        return this.consulta.getAgendamentos();
    }
}
