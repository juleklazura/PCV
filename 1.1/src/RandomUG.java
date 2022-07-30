import java.util.*;
import java.util.Random;

public class RandomUG {
  static int maxVertex = 10;

  // Function to generate random graph
  public static void generateRandomGraphs(int e)
  {
    int i = 0, j = 0, count = 0;

    int[][] edge = new int[e][2];
    Random rand = new Random();

    // Build a connection between two random vertex
    while (i < e) {
      edge[i][0] = rand.nextInt(maxVertex) + 1;
      edge[i][1] = rand.nextInt(maxVertex) + 1;

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
        }

        else if (edge[j][1] == i + 1) {
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
  }

  public static void main(String args[]) throws Exception
  {
    int e = 6;
    System.out.println("Enter the number of Edges : " +e);

    // Function to generate a Random unDirected Graph
    generateRandomGraphs(e);
  }
}