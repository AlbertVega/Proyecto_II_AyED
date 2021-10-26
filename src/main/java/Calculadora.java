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
        writeDataAtEnd("D:\\TEC\\II Semestre\\Algoritmos y Estructuras de Datos I\\Proyecto_II_AyED\\src\\main\\java\\CSV.txt");
        CSVtoArray("D:\\TEC\\II Semestre\\Algoritmos y Estructuras de Datos I\\Proyecto_II_AyED\\src\\main\\java\\CSV.txt");
    }

    public void readCSVColumn() {
        try {

            CSVReader reader = new CSVReader(new FileReader("D:\\TEC\\II Semestre\\Algoritmos y Estructuras de Datos I\\Proyecto_II_AyED\\src\\main\\java\\CSV.txt"));

            String[] nextLine;
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

    public void CSVtoArray(String filePath){
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
        } catch (IOException e) {
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
            String[] header = {"Expresión", "Resultado", "Fecha"};
            writer.writeNext(header);
            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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