import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Calculadora implements Serializable {
    public static String infix;
    public String postfix;
    public String resultado;

    public String getResultado() { return resultado; }

    public void setResultado(String resultado) { this.resultado = resultado; }

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        Calculadora.infix = infix;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public void calcular(){
        postfix = InfixToPostfix.toPostfix(infix);
        ExpressionTree et = new ExpressionTree();
        char[] charArray = postfix.toCharArray();
        Node root = et.constructTree(charArray);
        System.out.println("infix expression is");
        et.inorder(root);
        //resultado = Integer.toString(Integer.parseInt(postfix));
        writeDataLineByLine("C:\\Users\\huawei\\Documents\\GitHub\\Proyecto_II_AyED\\src\\main\\java\\CSV.txt");
    }

    public void writeDataLineByLine(String filePath) {
        // first create file object for file placed at location
        // specified by filepath

        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "Expresión", "Resultado", "Fecha" };
            writer.writeNext(header);

            // add data to csv
            String[] data1 = { };
            writer.writeNext(data1);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
