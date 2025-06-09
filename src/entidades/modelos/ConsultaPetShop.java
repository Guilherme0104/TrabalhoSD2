package entidades.modelos;

import java.util.ArrayList;

public class ConsultaPetShop extends ConsultaBase{
  private ArrayList<String> produtos;

  public ConsultaPetShop() {
    super();
    this.produtos = new ArrayList<>();
  }

  public ArrayList<String> getProdutos() {
    return produtos;
  }

  public void adicionarProduto(String produto) {
    produtos.add(produto);
  }

  public void removerProduto(int id) {
    if (id < produtos.size() && id >= 0) {
      produtos.remove(id);
    } else {
      throw new IllegalArgumentException("Produto " + id + " nao encontrado.");
    }
  }
  
  @Override
  public String toString() {
    return "Tipo: PETSHOP\nProdutos: " + produtos + "\nAgendamentos: " + agendamentos;
  }
}