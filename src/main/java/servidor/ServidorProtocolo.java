package servidor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entidades.controladores.Agendamento;
import entidades.animais.Animal;
import entidades.interfaces.IProtocoloServidor;
import modelos.ConsultaVeterinaria;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServidorProtocolo extends UnicastRemoteObject implements IProtocoloServidor {
    private final ConsultaVeterinaria consultaServico;
    private final Gson gson;

    public ServidorProtocolo() throws RemoteException {
        super();
        this.consultaServico = new ConsultaVeterinaria();
        this.gson = new GsonBuilder()
            .registerTypeAdapter(Animal.class, new AnimalAdapter())
            .create();
    }

    @Override
    public byte[] processarRequisicao(byte[] requisicao) throws RemoteException {
        String jsonRequisicao = new String(requisicao);
        Mensagem requisicaoMsg = gson.fromJson(jsonRequisicao, Mensagem.class);

        Object resposta = null;
        String erro = null;

        try {
            switch (requisicaoMsg.getMethodId()) {

                case "realizarConsulta":
                    // Agendamento completo, com Animal e Veterinario
                    Agendamento agendamento = gson.fromJson(requisicaoMsg.getArguments(), Agendamento.class);
                    consultaServico.realizarConsulta(agendamento);
                    break;

                case "cancelarConsulta":
                    int id = gson.fromJson(requisicaoMsg.getArguments(), Integer.class);
                    consultaServico.cancelarConsulta(id);
                    break;

                case "getMedicamentos":
                    String animalTipo = gson.fromJson(requisicaoMsg.getArguments(), String.class);
                    resposta = consultaServico.getMedicamentos(animalTipo);
                    break;

                case "aplicarMedicamento":
                    // args = JSON array [animalTipo, medicamento, quantidade]
                    String[] argsAplicar = gson.fromJson(requisicaoMsg.getArguments(), String[].class);
                    consultaServico.aplicarMedicamento(argsAplicar[0], argsAplicar[1], Integer.parseInt(argsAplicar[2]));
                    break;

                case "adicionarMedicamento":
                    // args = JSON array [animalTipo, medicamento, quantidade]
                    String[] argsAdicionar = gson.fromJson(requisicaoMsg.getArguments(), String[].class);
                    consultaServico.adicionarMedicamento(argsAdicionar[0], argsAdicionar[1], Integer.parseInt(argsAdicionar[2]));
                    break;

                case "adicionarAnimal":
                    Animal novoAnimal = gson.fromJson(requisicaoMsg.getArguments(), Animal.class);
                    consultaServico.adicionarAnimal(novoAnimal);
                    break;

                case "removerAnimal":
                    int idAnimal = gson.fromJson(requisicaoMsg.getArguments(), Integer.class);
                    consultaServico.removerAnimal(idAnimal);
                    break;

                case "getAnimais":
                    resposta = consultaServico.getAnimais();
                    break;

                case "getAgendamentos":
                    resposta = consultaServico.getAgendamentos();
                    break;

                default:
                    throw new IllegalArgumentException("Método desconhecido: " + requisicaoMsg.getMethodId());
            }

        } catch (Exception e) {
            erro = e.getMessage();
            e.printStackTrace(); // útil para debug no servidor
        }

        // Criar resposta
        Mensagem respostaMsg = new Mensagem();
        respostaMsg.setMessageType(1); // 1 = reply
        respostaMsg.setRequestId(requisicaoMsg.getRequestId());

        if (erro != null) {
            respostaMsg.setArguments(gson.toJson(erro));
        } else if (resposta != null) {
            respostaMsg.setArguments(gson.toJson(resposta));
        } else {
            respostaMsg.setArguments(gson.toJson("Operação executada com sucesso"));
        }

        String jsonResposta = gson.toJson(respostaMsg);
        return jsonResposta.getBytes();
    }
}
