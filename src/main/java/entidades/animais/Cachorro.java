package entidades.animais;

public class Cachorro extends Animal {
  
  public Cachorro(String nome, int idade, String raca) {
    super(nome, idade, raca);
  }

  @Override
  public void emitirSom() {
    System.out.println("Au au");
  }
}