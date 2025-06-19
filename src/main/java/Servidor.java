import entidades.interfaces.IProtocoloServidor;
import servidor.ServidorProtocolo;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String[] args) {
        try {
            try {
                LocateRegistry.createRegistry(1099);
                System.out.println("RMI Registry iniciado na porta 1099.");
            } catch (Exception e) {
                System.out.println("RMI Registry já estava em execução.");
            }

            IProtocoloServidor servidor = new ServidorProtocolo();

            Naming.rebind("rmi://localhost:1099/ProtocoloServidor", servidor);

            System.out.println("Servidor RMI com protocolo iniciado e aguardando requisições...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
