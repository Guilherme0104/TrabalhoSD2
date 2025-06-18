package servidor;

import com.google.gson.Gson;

import entidades.interfaces.IProtocoloServidor;
import modelos.ConsultaVeterinaria;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Date;



public class ServidorProtocolo extends UnicastRemoteObject implements IProtocoloServidor {
    private ConsultaVeterinaria consultaServico;
    private Gson gson;

    public ServidorProtocolo() throws RemoteException {
        super();
        this.consultaServico = new ConsultaVeterinaria();
        this.gson = new Gson();
    }

    @Override
    public byte[] processarRequisicao(byte[] requisicao) throws RemoteException {

         //desempacota
        String jsonRequisicao = Arrays.toString(requisicao);
        Mensagem requisicaoMsg = gson.fromJson(jsonRequisicao, Mensagem.class);

        Object resposta = null;
        String erro = null;
        try {

            switch (requisicaoMsg.getMethodId()) {
                case "realizarConsulta":
                    // O argumento vem como JSON, precisamos converter para o tipo certo
                    Date data = gson.fromJson(requisicaoMsg.getArguments(), Date.class);
                    consultaServico.realizarConsulta(data);
                    System.out.println("Consulta agendada via protocolo para: " + data);
                    break;
                case "cancelarConsulta":
                    int id = gson.fromJson(requisicaoMsg.getArguments(), Integer.class);
                    consultaServico.cancelarConsulta(id);
                    break;
                case "getMedicamentos":
                    String animal = gson.fromJson(requisicaoMsg.getArguments(), String.class);
                    resposta = consultaServico.getMedicamentos(animal);
                    break;
                // Adicione o 4º método aqui...
                default:
                    throw new IllegalArgumentException("Método desconhecido: " + requisicaoMsg.getMethodId());
            }
           


        } catch (Exception e) {
            erro = e.getMessage();
        }
        // 3. Empacotar a resposta
        Mensagem respostaMsg = new Mensagem();
        respostaMsg.setMessageType(1); // 1 = Reply
        respostaMsg.setRequestId(requisicaoMsg.getRequestId());

        if(erro != null) {
            respostaMsg.setArguments(gson.toJson(erro)); // Envia a mensagem de erro
        } else if (resposta != null) {
            respostaMsg.setArguments(gson.toJson(resposta)); // Converte o resultado para JSON
        }

        String jsonResposta = gson.toJson(respostaMsg);
        return jsonResposta.getBytes();
    }

}
