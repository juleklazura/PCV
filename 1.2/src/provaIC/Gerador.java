import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Random;

public class Gerador {
    static int maxVertex = 15;

    // Function to generate random graph
    public static void generateRandomGraphs(int e, String file)
            throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = null;

        writer = new PrintWriter(file + ".txt", "UTF-8");

        e = e * (e - 1) / 2;
        System.out.println("e = " + e);

        int i = 0;
        List<Vertice> vertices = new ArrayList<>();
        Random rand = new Random();

        do {
            for (int p = 0; p <= maxVertex - 2; p++) {
                for (int q = p + 1; q <= maxVertex - 1; q++) {
                    Vertice vertice = new Vertice();
                    vertice.origem = p + 1;
                    vertice.destino = q + 1;
                    vertice.peso = rand.nextInt(1, 10);
                    vertices.add(i, vertice);
                    writer.print(vertice.linhaDoArquivo());
                    i++;
                }
            }
            if (i >= e) {
                break;
            }
        } while (i <= e);
        writer.close();
    }

    public static void main(String args[]) throws Exception {
        int e = 15;

        for (int x = 0; x < 3; x++) {
            generateRandomGraphs(e, "grafo_ic_" + (x + 1));
        }
    }
}