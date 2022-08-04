import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainPCV {

    static String caminho = "C:/Users/chaga/OneDrive/Área de Trabalho/UEPG/4º Ano/Inteligencia Computacional/PCV/pcv/1.2/src/provaIC/";
    static String arqGrafos[] = { "grafo_ic_1.txt", "grafo_ic_2.txt", "grafo_ic_3.txt" };

    /**
     * @param index
     * @return
     * @throws FileNotFoundException
     */
    public static void leituraDoGrafo() throws FileNotFoundException {

        List<List<Vertice>> grafos = new ArrayList<>();
        List<Population> populations = new ArrayList<>();
        for (int ind = 0; ind < 3; ind++) {
            List<Vertice> vertices = new ArrayList<>();
            File file = new File(caminho + arqGrafos[ind]);
            Scanner in = new Scanner(file);

            int i = 0, numVertices = 0;
            while (in.hasNext()) {
                String v = in.next();
                String vt[] = v.split(",");
                Vertice vertice = new Vertice();
                if (vt.length > 1) {
                    vertice.origem = Integer.parseInt(vt[0]);
                    i = vertice.origem - 1;
                    vertice.destino = Integer.parseInt(vt[1]);
                    vertice.peso = Integer.parseInt(vt[2]);
                    /*System.out.println(numVertices + " : " + i + " - (" + vertice.origem + "," + vertice.destino
                            + ") = " + vertice.peso);*/
                    vertices.add(vertice);
                    numVertices++;
                }
            }
            in.close();
            Population p_inicial = new Population(50, 0.5, vertices);
            //System.out.println((i + 1) + "º População criada...");
            populations.add(p_inicial);
            grafos.add(vertices);
        }

        List<Path> melhoriasPath = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Path pathMelhor = new Path(15, true);
            pathMelhor.custoPath(grafos.get(i));
            melhoriasPath.add(pathMelhor);
        }

        int cont = 0;
        while (cont < 100) {
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 3; j++) {
                    populations.get(j).evolve2(grafos.get(j));
                }
            }

            for (int j = 0; j < 3; j++) {
                if (populations.get(j).getFittestPath().getFitness() > melhoriasPath.get(j).getFitness()) {
                    Path path = populations.get(j).getFittestPath();
                    melhoriasPath.add(j, path);
                }
            }
            cont++;
        }

        for (int j = 0; j < 3; j++) {
            System.out.println("Grafo " + (j + 1) + ": " + melhoriasPath.get(j).getInfo());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        leituraDoGrafo();

    }

}