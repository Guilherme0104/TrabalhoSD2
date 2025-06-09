package entidades.interfaces;

import java.util.Date;
import java.util.Map;

public interface Consulta {
  public void realizarConsulta(Date data);
  public void cancelarConsulta(int id);
  public Map<String, Integer> getMedicamentos(String animala);
}