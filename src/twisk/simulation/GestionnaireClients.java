package twisk.simulation;

import twisk.monde.Etape;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireClients {

    ArrayList<Client> lstClient  ;
    public GestionnaireClients(){
        this.lstClient = new ArrayList<>() ;
    }
    public void setClients(int... tabClients){
        this.nettoyer();
        for (int c : tabClients){
            lstClient.add(new Client(c)) ;
        }
    }
    public void allerA(int numeroClient, Etape etape, int rang){


        Iterator<Client> iterator = this.iterator() ;
        while(iterator.hasNext()){
            Client client = iterator.next() ;
            if(client.getNumeroClient() == numeroClient){
                client.allerA(etape,rang) ;
            }
        }
    }
    public void nettoyer(){
        this.lstClient.clear();

    }
    public Iterator<Client> iterator(){
        return this.lstClient.iterator() ;
    }

    public void afficherLstCLient(){
        System.out.print("Les clients : ");
        Iterator<Client> iterator = this.iterator() ;
        while(iterator.hasNext()){
            System.out.print(iterator.next().getNumeroClient()  +" ");
        }
    }
    public int nbClient(){
        return this.lstClient.size() ;
    }

}
