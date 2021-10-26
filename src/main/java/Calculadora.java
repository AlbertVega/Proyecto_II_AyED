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

    public String getResultado() {
        return resultado;
    }

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        Calculadora.infix = infix;
    }

    public void calcular(){
        postfix = InfixToPostfix.toPostfix(infix);
        ExpressionTree et = new ExpressionTree();
        char[] charArray = postfix.toCharArray();
        Node root = et.constructTree(charArray);
        System.out.println("infix expression is");
        et.inorder(root);
        resultado = Integer.toString(et.evalTree(root));
        writeDataLineByLine("C:\\Users\\huawei\\Documents\\GitHub\\Proyecto_II_AyED\\src\\main\\java\\CSV.txt");
        readCSVColumn();
    }

    public void readCSVColumn(){
        try {

            CSVReader reader = new CSVReader(new FileReader("C:\\Users\\huawei\\Documents\\GitHub\\Proyecto_II_AyED\\src\\main\\java\\CSV.txt"));

            String [] nextLine;
            int rowNumber = 0;
            while ((nextLine = reader.readNext()) != null) {
                rowNumber++;
                String name = nextLine[1];
                String name2 = nextLine[2];
            }
            System.out.println(Arrays.toString(nextLine));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
            java.util.Date fecha = new Date();
            // add data to csv
            String[] data1 = {infix, resultado, fecha.toString()};
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
