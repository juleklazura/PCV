public class Vertice {
    
    public int origem;
    public int destino;
    public int peso;

    public Vertice(){
        origem = 0;
        destino = 0;
        peso = 0;
    }

    public Vertice(int origem, int destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public String linhaDoArquivo(){
        return origem + "," + destino + "," + peso + "\n";
    }
    
}
