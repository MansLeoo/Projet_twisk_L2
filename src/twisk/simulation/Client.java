package twisk.simulation;

import twisk.monde.Etape;

public class Client {

    private int numeroClient ;
    private int rang ;

    private Etape etape ;
    public Client(int numero){
        this.numeroClient = numero ;
        this.etape = null ;
        this.rang = 0 ;
    }
    public void allerA(Etape etape, int rang){
        this.rang = rang ;
        this.etape = etape ;
    }

    public int getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(int numeroClient) {
        this.numeroClient = numeroClient;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }
}
