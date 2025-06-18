package Cliente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entidades.interfaces.IProtocoloServidor;
import java.lang.reflect.Type;
import java.rmi.Naming;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import servidor.Mensagem;

public class ConsultaProxy { // Não implementa 'Consulta', mas oferece os mesmos métodos
    private IProtocoloServidor servidorRemoto;
    private Gson gson;
    private static final AtomicInteger nextRequestId = new AtomicInteger(0);
    private final String objectReference = "ConsultaService";

    public ConsultaProxy() throws Exception {
        // Encontra o servidor de protocolo remoto
        this.servidorRemoto = (IProtocoloServidor) Naming.lookup("rmi://localhost:1099/ConsultaService");
        this.gson = new Gson();
    }

    // Este é o método que corresponde ao 'doOperation' do trabalho 
    private Mensagem doOperation(String methodId, Object... args) throws Exception {
        // Empacotar a requisição (Marshalling)
        Mensagem reqMsg = new Mensagem();
        reqMsg.setMessageType(0); // 0 = Request
        reqMsg.setRequestId(nextRequestId.getAndIncrement());
        reqMsg.setObjectReference(this.objectReference);
        reqMsg.setMethodId(methodId);
        
        // Converte os argumentos para JSON. Se não houver, envia um JSON vazio.
        reqMsg.setArguments(gson.toJson(args));

        String jsonRequisicao = gson.toJson(reqMsg);

        // Enviar e receber a resposta via RMI
        byte[] respostaBytes = servidorRemoto.processarRequisicao(jsonRequisicao.getBytes());

        // 3. Desempacotar a resposta (Unmarshalling)
        String jsonResposta = new String(respostaBytes);
        return gson.fromJson(jsonResposta, Mensagem.class);
    }
    
    // Métodos que o cliente realmente vai usar
    public void realizarConsulta(Date data) throws Exception {
        doOperation("realizarConsulta", data);
    }

    public void cancelarConsulta(int id) throws Exception {
        doOperation("cancelarConsulta", id);
    }

    public Map<String, Integer> getMedicamentos(String animal) throws Exception {
        Mensagem resposta = doOperation("getMedicamentos", animal);
        
        // O resultado está em JSON dentro da mensagem, precisamos convertê-lo de volta
        Type tipoMapa = new TypeToken<Map<String, Integer>>(){}.getType();
        return gson.fromJson(resposta.getArguments(), tipoMapa);
    }
}