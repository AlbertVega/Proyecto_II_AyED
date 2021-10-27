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
    static String infix;
    static String postfix;
    static String resultado;

    public List<String[]> getCsvArraylist() {
        return csvArraylist;
    }

    public List<String[]> csvArraylist;
    java.util.Date fecha = new Date();

    public Date getFecha() {
        return fecha;
    }

    public String getResultado() {
        return resultado;
    }

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        Calculadora.infix = infix;
    }

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