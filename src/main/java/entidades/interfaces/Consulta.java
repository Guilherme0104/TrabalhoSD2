package entidades.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entidades.animais.Animal;
import entidades.controladores.Agendamento;

public interface Consulta extends Remote{
  public void realizarConsulta(Agendamento agendamento) throws RemoteException;
  public void cancelarConsulta(int id) throws RemoteException;
  public Map<String, Integer> getMedicamentos(String animala) throws RemoteException;
  public void aplicarMedicamento(String animal, String medicamento, int quantidade) throws RemoteException;
  public void adicionarMedicamento(String animal, String medicamento, int quantidade) throws RemoteException;
  public void adicionarAnimal(Animal animal) throws RemoteException;
  public ArrayList<Animal> getAnimais() throws RemoteException;
  public void removerAnimal(int id) throws RemoteException;
  public List<Agendamento> getAgendamentos() throws RemoteException;
}