package entidades.controladores;

import java.io.Serializable;
import java.util.Date;

public class ConsultaRegistro implements Serializable {
    private String animal;
    private Date data;
    private String observacao;

    public ConsultaRegistro(String animal, Date data, String observacao) {
        this.animal = animal;
        this.data = data;
        this.observacao = observacao;
    }

    public String getAnimal() {
        return animal;
    }

    public Date getData() {
        return data;
    }

    public String getObservacao() {
        return observacao;
    }

    @Override
    public String toString() {
        return "Animal: " + animal + ", Data: " + data + ", Obs: " + observacao;
    }
}
