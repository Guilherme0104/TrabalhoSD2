package servidor;

public class Mensagem {
    private int messageType; // 0 para Request, 1 para Reply
    private int requestId;
    private String objectReference;
    private String methodId;
    private String arguments;

    public Mensagem() {
        // Construtor padrão
    }

    public Mensagem(int messageType, int requestId, String objectReference, String methodId, String arguments) {
        this.messageType = messageType;
        this.requestId = requestId;
        this.objectReference = objectReference;
        this.methodId = methodId;
        this.arguments = arguments;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getObjectReference() {
        return objectReference;
    }

    public void setObjectReference(String objectReference) {
        this.objectReference = objectReference;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getArguments() {
        return arguments;
    }
    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

}
