package entidades.animais;

import java.io.Serializable;

public abstract class Animal implements Serializable {
  private String nome;
  private int idade;
  private String raca;

  public Animal(String nome, int idade, String raca) {
    this.nome = nome;
    this.idade = idade;
    this.raca = raca;
  }

  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public int getIdade() {
    return idade;
  }
  public void setIdade(int idade) {
    this.idade = idade;
  }
  public String getRaca() {
    return raca;
  }
  public void setRaca(String raca) {
    this.raca = raca;
  }

  public abstract void emitirSom();

  @Override
  public String toString() {
    return "Animal [nome=" + nome + ", idade=" + idade + ", raca=" + raca + "]";
  }
}