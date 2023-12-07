package twisk.test;

import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.outils.FabriqueNumero;
import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;

import java.util.Iterator;

public class TestGestionnaireClient {

    @Test
    public void TestClient() {
        Activite zoo = new Activite("Zoo",6,3) ;
        Activite toboggan = new Activite("toboggan",6,3) ;

        Client client = new Client(2048);
        client.allerA(zoo,10);
        assert client.getEtape().getNom().equals("Zoo") : "Probleme Etape client" ;
        assert client.getRang() == 10 : "Probleme rang" ;
        assert client.getNumeroClient() == 2048 : "Probleme numéro client" ;
        client.allerA(toboggan,2);
        assert client.getEtape().getNom().equals("toboggan") : "Probleme Etape client" ;
        assert client.getRang() == 2 : "Probleme rang" ;
    }
    @Test
    public void TestGestionnaireClient(){
        int[] tab = {2048,2049,2050} ;
        Activite zoo = new Activite("Zoo",6,3) ;
        Activite toboggan = new Activite("toboggan",6,3) ;

        GestionnaireClients gestionnaire = new GestionnaireClients() ;
        gestionnaire.setClients(tab);
        gestionnaire.allerA(2048,zoo,10);
        gestionnaire.allerA(2050,toboggan,10);
        Iterator<Client> iterator = gestionnaire.iterator();
        Client[] tabCL = new Client[3] ;
        int i = 0 ;
        while (iterator.hasNext()){
            Client c = iterator.next();
            tabCL[i] = c ;
            i++ ;
        }
        assert tabCL[0].getEtape().getNom().equals(zoo.getNom()) : "Problème gestionnaire" ;
        assert tabCL[2].getEtape().getNom().equals(toboggan.getNom()) : "Problème gestionnaire" ;


    }

}
