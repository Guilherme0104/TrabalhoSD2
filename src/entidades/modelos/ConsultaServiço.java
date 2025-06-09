package entidades.modelos;

import entidades.interfaces.Consulta;
import entidades.modelos.ConsultaBase;
import entidades.modelos.ConsultaPetShop;
import entidades.modelos.ConsultaVeterinaria;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

public class ConsultaServiço extends UnicastRemoteObject implements Consulta {
    private ConsultaBase consulta;

    public ConsultaServiço() throws RemoteException {
       super();
    }


}
