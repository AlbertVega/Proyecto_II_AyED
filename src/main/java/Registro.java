import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ManagedBean
@RequestScoped
public class Registro {

    public List<VariablesRegistro> getLista() {
        return lista;
    }

    public void setLista(List<VariablesRegistro> lista) {
        this.lista = lista;
    }

    private List<VariablesRegistro> lista = new ArrayList<>();

    public void listarConsultas(){
        VariablesRegistro variables = new VariablesRegistro();
        variables.setExpresion(Calculadora.infix);
        variables.setResultado("0");
        variables.setFecha(String.valueOf(Calendar.getInstance()));
        lista.add(variables);
    }
}
