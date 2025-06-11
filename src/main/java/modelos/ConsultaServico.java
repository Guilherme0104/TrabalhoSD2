package modelos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Map;

import entidades.interfaces.Consulta;

public class ConsultaServico extends UnicastRemoteObject implements Consulta {
    private final ConsultaBase consulta;

    public ConsultaServico() throws RemoteException {
       super();

       this.consulta = new ConsultaVeterinaria();
    }

    @Override
    public void realizarConsulta(Date data) throws RemoteException {
        consulta.realizarConsulta(data);
        System.out.println("Nova consulta agendada para:" + data);
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


}
