import com.google.gson.Gson;
import entidades.animais.Animal;
import entidades.animais.Cachorro;
import entidades.animais.Gato;
import entidades.animais.Papagaio;
import entidades.controladores.Veterinario;
import entidades.interfaces.IProtocoloServidor;
import servidor.Mensagem;

import java.rmi.Naming;
import java.util.*;

public class Cliente {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Gson gson = new Gson();
    private static int requestId = 1;

    public static void main(String[] args) {
        try {
            IProtocoloServidor servidor = (IProtocoloServidor) Naming.lookup("rmi://localhost:1099/ProtocoloServidor");

            int opcao;

            do {
                System.out.println("\n=== CLIENTE PROTOCOLO ===");
                System.out.println("1. Realizar consulta");
                System.out.println("2. Cancelar consulta");
                System.out.println("3. Ver medicamentos por animal");
                System.out.println("4. Aplicar medicamento(Remover medicamentos)");
                System.out.println("5. Adicionar medicamento");
                System.out.println("6. Adicionar animal");
                System.out.println("7. Remover animal");
                System.out.println("8. Listar animais");
                System.out.println("9. Listar consultas");
                System.out.println("0. Sair");
                System.out.print("Escolha: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        realizarConsulta(servidor);
                        break;
                    case 2:
                        cancelarConsulta(servidor);
                        break;
                    case 3:
                        getMedicamentos(servidor);
                        break;
                    case 4:
                        aplicarMedicamento(servidor);
                        break;
                    case 5:
                        adicionarMedicamento(servidor);
                        break;
                    case 6:
                        adicionarAnimal(servidor);
                        break;
                    case 7:
                        removerAnimal(servidor);
                        break;
                    case 8:
                        listar(servidor, "getAnimais");
                        break;
                    case 9:
                        listar(servidor, "getAgendamentos");
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }

            } while (opcao != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String toJsonComTipoAnimal(Animal animal) {
        Map<String, Object> mapa = new LinkedHashMap<>();
        mapa.put("type", animal.getClass().getSimpleName());
        mapa.put("nome", animal.getNome());
        mapa.put("idade", animal.getIdade());
        mapa.put("raca", animal.getRaca());
        return gson.toJson(mapa);
    }

    private static void realizarConsulta(IProtocoloServidor servidor) throws Exception {
        Animal animal = criarAnimal();
        if (animal == null) return;

        System.out.print("Nome do veterinário: ");
        String vetNome = scanner.nextLine();

        System.out.print("Especialidade do veterinário: ");
        String vetEsp = scanner.nextLine();

        Veterinario vet = new Veterinario(vetNome, vetEsp);
        Map<String, Object> mapaAgendamento = new LinkedHashMap<>();
        mapaAgendamento.put("data", new Date());
        mapaAgendamento.put("animal", gson.fromJson(toJsonComTipoAnimal(animal), Map.class));
        mapaAgendamento.put("veterinario", vet);
        enviar(servidor, "realizarConsulta", gson.toJson(mapaAgendamento));
    }

    private static void cancelarConsulta(IProtocoloServidor servidor) throws Exception {
        System.out.print("ID da consulta a cancelar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        enviar(servidor, "cancelarConsulta", gson.toJson(id));
    }

    private static void getMedicamentos(IProtocoloServidor servidor) throws Exception {
        System.out.print("Tipo de animal (ex: Cachorro): ");
        String tipo = scanner.nextLine();
        String resposta = enviar(servidor, "getMedicamentos", gson.toJson(tipo));
        System.out.println("Medicamentos: " + resposta);
    }

    private static void aplicarMedicamento(IProtocoloServidor servidor) throws Exception {
        System.out.print("Tipo de animal: ");
        String animal = scanner.nextLine();
        System.out.print("Medicamento: ");
        String medicamento = scanner.nextLine();
        System.out.print("Quantidade: ");
        int qtd = scanner.nextInt();
        scanner.nextLine();

        String[] args = {animal, medicamento, String.valueOf(qtd)};
        enviar(servidor, "aplicarMedicamento", gson.toJson(args));
    }

    private static void adicionarMedicamento(IProtocoloServidor servidor) throws Exception {
        System.out.print("Tipo de animal: ");
        String animal = scanner.nextLine();
        System.out.print("Medicamento: ");
        String medicamento = scanner.nextLine();
        System.out.print("Quantidade: ");
        int qtd = scanner.nextInt();
        scanner.nextLine();

        String[] args = {animal, medicamento, String.valueOf(qtd)};
        enviar(servidor, "adicionarMedicamento", gson.toJson(args));
    }

    private static void adicionarAnimal(IProtocoloServidor servidor) throws Exception {
        Animal animal = criarAnimal();
        if (animal != null) {
            enviar(servidor, "adicionarAnimal", toJsonComTipoAnimal(animal));
        }
    }

    private static void removerAnimal(IProtocoloServidor servidor) throws Exception {
        System.out.print("ID do animal a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        enviar(servidor, "removerAnimal", gson.toJson(id));
    }

    private static void listar(IProtocoloServidor servidor, String metodo) throws Exception {
        String resposta = enviar(servidor, metodo, "");
        System.out.println(resposta);
    }

    private static String enviar(IProtocoloServidor servidor, String metodo, String argsJson) throws Exception {
        Mensagem msg = new Mensagem();
        msg.setMessageType(0);
        msg.setMethodId(metodo);
        msg.setArguments(argsJson);
        msg.setRequestId(requestId++);

        String json = gson.toJson(msg);
        byte[] respostaBytes = servidor.processarRequisicao(json.getBytes());
        String jsonResp = new String(respostaBytes);
        Mensagem resposta = gson.fromJson(jsonResp, Mensagem.class);

        System.out.println("→ Resposta: " + resposta.getArguments());
        return resposta.getArguments();
    }

    private static Animal criarAnimal() {
        System.out.print("Nome do animal: ");
        String nome = scanner.nextLine();
        System.out.print("Idade do animal: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Raça do animal: ");
        String raca = scanner.nextLine();

        System.out.println("Tipo do animal (1 - Cachorro, 2 - Gato, 3 - Papagaio): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        switch (tipo) {
            case 1:
                return new Cachorro(nome, idade, raca);
            case 2:
                return new Gato(nome, idade, raca);
            case 3:
                return new Papagaio(nome, idade, raca);
            default:
                System.out.println("Tipo inválido.");
                return null;
        }
    }
}
