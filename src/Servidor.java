import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import modelos.ConsultaServico;


public class Servidor {
    public static void main(String[] args){
        try{
            ConsultaServico servico = new ConsultaServico();

            LocateRegistry.createRegistry(1099);
            
            Naming.rebind("rmi://localhost:1099/ConsultaService", servico);

            System.out.println("Servidor de consulta iniciado e aguardando conexões...");
        } catch(Exception e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
