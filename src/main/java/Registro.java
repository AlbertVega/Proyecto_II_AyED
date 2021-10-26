import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@RequestScoped
public class Registro {

    public static List<VariablesRegistro> getLista() {
        return lista;
    }

    public void setLista(List<VariablesRegistro> lista) {
        Registro.lista = lista;
    }

    private static List<VariablesRegistro> lista = new ArrayList<>();

    public void listarConsultas(){
        VariablesRegistro variables = new VariablesRegistro();
        variables.setExpresion(Calculadora.infix);
        variables.setResultado(Calculadora.resultado);
        //variables.setFecha(fecha.toString());
        lista.add(variables);
    }
}
