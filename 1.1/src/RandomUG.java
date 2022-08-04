/*import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Random;

public class RandomUG {
  static int maxVertex = 15;

  // Function to generate random graph
  public static void generateRandomGraphs(int e, String nomeArquivo) throws FileNotFoundException, UnsupportedEncodingException {
    PrintWriter writer = null;

    writer = new PrintWriter(nomeArquivo + ".txt", "UTF-8");
    
    int i = 0;
    String linha;
    Integer[] aresta = new Integer[3];
    ArrayList<Integer[]> listaArestas = new ArrayList();
    Random rand = new Random();

    while (i < e) {
      for (int p = 0; p <= maxVertex - 2; p++) {
        for (int q = p + 1; q <= maxVertex - 1; q++) {
          aresta[0] = p + 1;
          aresta[1] = q + 1;
          aresta[2] = rand.nextInt(10) + 1;
          listaArestas.add(i, aresta);
          linha = "vA" + i + " = " + listaArestas.get(i)[0] +
              "      vB" + i + " = " + listaArestas.get(i)[1] + "           "
              + "peso = " + listaArestas.get(i)[2] + " \n";
          writer.print(linha);
          aresta = new Integer[3];
          i++;
        }
      }
      if (i >= e)
        break;

      aresta[0] = rand.nextInt(maxVertex) + 1;
      aresta[1] = rand.nextInt(maxVertex) + 1;
      aresta[2] = rand.nextInt(10) + 1;
      listaArestas.add(i, aresta);

      linha = "vA" + i + " = " + listaArestas.get(i)[0] +
          "      vB" + i + " = " + listaArestas.get(i)[1] + "          "
          + " peso = " + listaArestas.get(i)[2] + " \n";

      writer.print(linha);
      aresta = new Integer[3];
      i++;
    }

    // Loop-End
    writer.print("vA" + i + " = " + listaArestas.get(i - 1)[0] +
        "      vB" + i + " = " + listaArestas.get(i - 1)[1] + "           "
        + "peso = " + listaArestas.get(i - 1)[2] + " \n");

    writer.close();
  }

  public static void main(String args[]) throws Exception {
    // Scanner leitura = new Scanner(System.in);
    int e = 15;
    // System.out.println("Enter the number of Edges : ");
    // e = leitura.nextInt();

    // Function to generate a Random unDirected Graph
    generateRandomGraphs(e);
  }
}*/