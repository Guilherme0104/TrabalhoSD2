# Trabalho 2 – Remote Method Invocation (RMI)

**Disciplina**: Sistemas Distribuídos  
**Professor**: Rafael Braga  
**Autores**: José Vitor de Oliveira Nobre - 509295 && Luis Guilherme Xavier da Silva - 540056

---

## 🎯 Objetivo

Este trabalho teve como objetivo reimplementar o sistema distribuído desenvolvido anteriormente utilizando **Java RMI (Remote Method Invocation)** com um protocolo de **requisição-resposta empacotado**, conforme descrito no capítulo 5.2 do livro texto da disciplina. A comunicação entre cliente e servidor se dá por meio da troca de mensagens `byte[]` contendo dados em formato **JSON**, serializados com a biblioteca **Gson**.

---

## ⚙️ Estrutura do Sistema

O sistema simula o funcionamento de um **petshop**, permitindo o agendamento de consultas, controle de medicamentos e cadastro de animais. Os principais componentes do sistema são:

### 🧱 Entidades

- `Animal` (abstrata)
  - `Cachorro`
  - `Gato`
  - `Papagaio`
- `Veterinario`
- `Agendamento`
- `Estoque`

### 🔁 Relações

- `Animal` é estendido por `Cachorro`, `Gato` e `Papagaio` (**herança**)
- `ConsultaVeterinaria` **possui** um `Estoque` e uma lista de `Agendamentos` (**agregação**)
- `Agendamento` agrupa um `Animal` e um `Veterinario`

---

## 🔌 Comunicação Cliente-Servidor

A comunicação foi implementada via RMI, **sem uso direto de sockets**. Para simular o protocolo de requisição-resposta, foram utilizados:

- Uma interface remota `IProtocoloServidor`, com o método:
  ```java
  byte[] processarRequisicao(byte[] requisicao) throws RemoteException;

    Uma classe Mensagem, representando requisições e respostas com os seguintes campos:

        messageType (0 = request, 1 = reply)

        methodId

        arguments (JSON)

        requestId

### 🧠 Lógica do Servidor

A classe ServidorProtocolo interpreta a requisição recebida, identifica o método a ser invocado, executa-o localmente e retorna a resposta empacotada em JSON.

Para contornar o problema de serialização de classes abstratas com Gson (Animal), foi criado um adaptador personalizado AnimalAdapter que lê o campo "type" e instancia a subclasse correta (Cachorro, Gato, Papagaio).
### 💻 Interface do Cliente

O cliente possui um menu interativo no terminal com as seguintes opções:

1. Realizar consulta

2. Cancelar consulta

3. Ver medicamentos por animal

4. Aplicar medicamento

5. Adicionar medicamento

6. Adicionar animal

7. Remover animal

8. Listar animais

9. Listar agendamentos

0. Sair

As operações são empacotadas como Mensagem, enviadas via RMI para o servidor, e exibem as respostas decodificadas ao usuário.
