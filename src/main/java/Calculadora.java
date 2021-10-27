import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Calculadora implements Serializable {
    /**
     * Almacena la operación en notación infija.
     */
    static String infix;
    /**
     * Almacena la operación en notación postfija.
     */
    static String postfix;
    /**
     * Almacena el resultado de la operación.
     */
    static String resultado;
    /**
     * Almacena un arraylist del CSV.
     */
    public List<String[]> csvArraylist;
    /**
     * Almacena un objeto con la información de la fecha y hora.
     */
    java.util.Date fecha = new Date();

    /**
     * Obtiene el Arraylist del csv.
     * @return csvArraylist
     */
    public List<String[]> getCsvArraylist() {
        return csvArraylist;
    }

    /**
     * Obtiene la variable resultado.
     * @return resultado
     */
    public String getResultado() {
        return resultado;
    }
    /**
     * Obtiene la variable infix.
     * @return infix
     */
    public String getInfix() {
        return infix;
    }

    /**
     * Asigna un string a la variable infix.
     * @param infix operación en notación infija
     */
    public void setInfix(String infix) {
        Calculadora.infix = infix;
    }

    /**
     * Se encarga de convertir de construir el árbol de expresión, resolverlo y almacenar el resultado en el csv.
     */
    public void calcular() {
        postfix = InfixToPostfix.toPostfix(infix);
        ExpressionTree et = new ExpressionTree();
        char[] charArray = postfix.toCharArray();
        Node root = et.constructTree(charArray);
        System.out.println("infix expression is");
        et.inorder(root);
        resultado = Integer.toString(et.evalTree(root));
        writeDataAtEnd("C:\\Users\\huawei\\Documents\\GitHub\\Proyecto_II_AyED\\src\\main\\java\\CSV.txt");
        csvArraylist = CSVtoArray("C:\\Users\\huawei\\Documents\\GitHub\\Proyecto_II_AyED\\src\\main\\java\\CSV.txt");
    }

    /**
     * Convierto el documento del csv en un arraylist.
     * @param filePath Ubicación del archivo csv
     * @return csvArraylist
     */
    public List<String[]> CSVtoArray(String filePath){
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
            r.remove(0);
            return r;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Escribe en el archivo csv la última operación con su resultado y fecha.
     * @param filePath Ubicación del archivo csv
     */
    public void writeDataAtEnd(String filePath) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(filePath, true));

            //Verifying the read data here
            String[] data = {infix, resultado, fecha.toString()};
            writer.writeNext(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}