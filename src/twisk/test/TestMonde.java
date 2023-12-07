package twisk.test;

import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.outils.FabriqueNumero;
public class TestMonde {






    @Test
    public void TestSemaphore() {
        FabriqueNumero.getInstances().reset();
        Guichet guichet = new Guichet("guichet");
        Guichet guichet2 = new Guichet("guichet2");
        assert guichet.getSemaphore() == 1 : "Pb semaphore" ;
        assert guichet2.getSemaphore() == 2 : "Pb semaphore" ;

    }
    @Test
    public void TestCompteur() {
        FabriqueNumero.getInstances().reset();
        Activite toboggan = new Activite("Toboggan");
        Activite toboggan2 = new Activite("Toboggan2");
        Activite zoo = new Activite("zoo");
        Guichet guichet = new Guichet("guichet");
        assert guichet.getNb() == 3 : "Pb compteur" ;
        assert zoo.getNb() == 2 : "Pb compteur" ;

    }
    @Test
    public void TestNbGuichet() {

        Activite toboggan = new Activite("Toboggan");
        Activite toboggan2 = new Activite("Toboggan2");
        Activite zoo = new Activite("zoo");
        Guichet guichet = new Guichet("guichet");
        Guichet guichet2 = new Guichet("guichet2");
        Guichet guichet3 = new Guichet("guichet3");
        Monde monde = new Monde();
        monde.ajouter(toboggan,toboggan2,zoo,guichet,guichet2,guichet3);
        assert monde.nbGuichet() == 3 : "Pb nbGuichet" ;
        Monde monde2 = new Monde();
        assert monde2.nbGuichet() == 0 : "Pb nbGuichet" ;
    }
    @Test
    public void TestNbEtape() {
        Activite toboggan = new Activite("Toboggan");
        Activite toboggan2 = new Activite("Toboggan2");
        Activite zoo = new Activite("zoo");
        Guichet guichet = new Guichet("guichet");
        Guichet guichet2 = new Guichet("guichet2");
        Guichet guichet3 = new Guichet("guichet3");
        Monde monde = new Monde();
        monde.ajouter(toboggan,toboggan2,zoo,guichet,guichet2,guichet3);
        assert monde.nbEtapes() == 6 : "Pb nbEtapes" ;
        Monde monde2 = new Monde();
        assert monde2.nbEtapes() == 0 : "Pb nbEtapes" ;
    }
    @Test
    public void TestACommeEntree() {
        Activite toboggan = new Activite("Toboggan");
        Activite zoo = new Activite("zoo");
        Guichet guichet = new Guichet("guichet");
        Monde monde = new Monde();
        Monde monde2 = new Monde();
        Monde monde3 = new Monde();
        monde.aCommeEntree(zoo);
        monde2.aCommeEntree(toboggan,zoo);
        monde3.aCommeEntree(guichet);
        assert monde.getSasEntree().getNbSuccesseur() == 1 :"Probleme aCommeEntree : activite ";
        assert monde2.getSasEntree().getNbSuccesseur() == 2 :"Probleme aCommeEntree : activite & actvite";
        assert monde3.getSasEntree().getNbSuccesseur() == 1 :"Probleme aCommeEntree : Guichet ";
    }
    @Test
    public void TestACommeSortie() {
        Activite toboggan = new Activite("Toboggan");
        Activite toboggan2 = new Activite("Toboggan2");

        Activite zoo = new Activite("zoo");
        Guichet guichet = new Guichet("guichet");
        Monde monde = new Monde();
        Monde monde2 = new Monde();
        Monde monde3 = new Monde();
        monde.aCommeSortie(zoo);
        monde2.aCommeSortie(toboggan,toboggan2);
        monde3.aCommeSortie(guichet);
        assert zoo.getNbSuccesseur() == 1 :"Probleme aCommeSortie : activite ";
        assert toboggan2.getNbSuccesseur() == 1 &&  toboggan.getNbSuccesseur() == 1:"Probleme aCommeSortie : activite & actvite";
        assert guichet.getNbSuccesseur() == 1 :"Probleme aCommeSortie : Guichet ";
    }
}
