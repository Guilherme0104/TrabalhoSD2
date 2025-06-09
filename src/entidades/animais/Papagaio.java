package entidades.animais;

public class Papagaio extends Animal {
  
  public Papagaio(String nome, int idade, String raca) {
    super(nome, idade, raca);
  }

  @Override
  public void emitirSom() {
    System.out.println("Prrr Prrr");
  }
}