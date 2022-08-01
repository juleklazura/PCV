import java.io.PrintWriter;
import java.util.*;
import java.util.Random;

public class RandomUG {
  static int maxVertex = 15;

  // Function to generate random graph
  public static void generateRandomGraphs(int e) {
    int i = 0, j = 0, count = 0;

    // e, número de linhas da matriz ou número de arestas
    // 3, número de colunas | indice: 2 - peso da aresta e
    int[][] edge = new int[e][3];
    Random rand = new Random();

    // Build a connection between two random vertex
    while (i < e) {
      edge[i][0] = rand.nextInt(maxVertex) + 1;
      edge[i][1] = rand.nextInt(maxVertex) + 1;
      edge[i][2] = rand.nextInt(1, 10); // peso da aresta

      // using rand to pick a random integer in range
      // of (1 - maxVertex)
      i++;
    }

    System.out.println(
        "The Generated Random Graph is :");

    // Print the Graph
    for (i = 0; i < maxVertex; i++) {
      count = 0;

      System.out.print((i + 1) + " -> { ");

      for (j = 0; j < e; j++) {
        if (edge[j][0] == i + 1) {
          System.out.print(edge[j][1] + " ");
          count++;
        } else if (edge[j][1] == i + 1) {
          System.out.print(edge[j][0] + " ");
          count++;
        }
        // print “Isolated vertex” for the vertex
        // having zero degree.
        else if (j == e - 1 && count == 0)
          System.out.print("Isolated Vertex!");
      }
      System.out.print(" }\n");
    }

    for (i = 0; i < e; i++) {
      for (j = 0; j < e; j++) {
        if (edge[j][0] == i + 1) {
          System.out.println((i+1)+","+edge[j][1]+","+edge[j][2]);
        } else if (edge[j][1] == i + 1) {
          System.out.println((i+1)+","+edge[j][0]+","+edge[j][2]);
        }
      }
    }

  }

  public static void main(String args[]) throws Exception {
    // Scanner leitura = new Scanner(System.in);
    int e = 15;
    // System.out.println("Enter the number of Edges : ");
    // e = leitura.nextInt();

    // Function to generate a Random unDirected Graph
    generateRandomGraphs(e);
  }
}