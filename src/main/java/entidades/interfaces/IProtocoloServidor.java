package entidades.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProtocoloServidor extends Remote  {
     /**
     * Processa uma requisição empacotada e retorna uma resposta empacotada.
     * @param requisicao Array de bytes contendo a mensagem de requisição.
     * @return Array de bytes contendo a mensagem de resposta.
     * @throws RemoteException
     */
    byte[] processarRequisicao(byte[] req) throws RemoteException;

}
