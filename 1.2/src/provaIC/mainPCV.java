import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tsp.Population;

public class mainPCV {

    static String caminho = "C:/Users/chaga/OneDrive/Área de Trabalho/UEPG/4º Ano/Inteligencia Computacional/PCV/pcv/1.2/src/provaIC/";
    static String arqGrafos[] = { "grafo1.txt", "grafo2.txt", "grafo3.txt" };

    /**
     * @param index
     * @return
     * @throws FileNotFoundException
     */
    public static void leituraDoGrafo() throws FileNotFoundException {
        for (int ind = 0; ind < 1; ind++) {
            List<Population> populations = new ArrayList<>();
            int vet[][] = new int[15][3];
            File file = new File(caminho + arqGrafos[ind]);
            Scanner in = new Scanner(file);

            int i = 0;
            while (in.hasNext()) {
                String v = in.next();
                String vt[] = v.split(",");
                int nodo1 = 0, nodo2 = 0, peso = 0;
                if (vt.length > 1) {
                    nodo1 = Integer.parseInt(vt[0]);
                    i = nodo1-1;
                    nodo2 = Integer.parseInt(vt[1]);
                    peso = Integer.parseInt(vt[2]);
                    vet[i][0] = nodo1;
                    vet[i][1] = nodo2;
                    vet[i][2] = peso;
                    System.out.println(i+" - (" + vet[i][0] + "," + vet[i][1] + ") = " + vet[i][2]);
                    //i++;
                }
            }
            in.close();
            Population p = new Population(5, 0.5, vet);
            populations.add(p);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        leituraDoGrafo();

    }

}