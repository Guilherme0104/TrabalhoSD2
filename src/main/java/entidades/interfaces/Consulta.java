package entidades.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

public interface Consulta extends Remote{
  public void realizarConsulta(Date data) throws RemoteException;
  public void cancelarConsulta(int id) throws RemoteException;
  public Map<String, Integer> getMedicamentos(String animala) throws RemoteException;
}