package twisk.test;
import org.junit.jupiter.api.Test;
import twisk.monde.*;
import twisk.outils.FabriqueNumero;
public class testEtape {

    @Test
    public void TestAjouterSuccesseur() {
        Activite toboggan = new Activite("Toboggan");
        Activite zoo = new Activite("zoo");
        Guichet guichet = new Guichet("guichet");
        Guichet guichet2 = new Guichet("guichet2");
        Guichet guichet3 = new Guichet("guichet3");

        toboggan.ajouterSuccesseur(zoo);
        guichet.ajouterSuccesseur(zoo);
        guichet2.ajouterSuccesseur(guichet);
        guichet3.ajouterSuccesseur(guichet,zoo);
        assert toboggan.getNbSuccesseur() == 1 :"Probleme Successeur : activite vers activite";
        assert guichet.getNbSuccesseur() == 1 :"Probleme Successeur : guichet vers actvite";
        assert guichet2.getNbSuccesseur() == 1 :"Probleme Successeur : Guichet vers guichet";
        assert guichet3.getNbSuccesseur() == 2 :"Probleme Successeur : Guichet vers guichet & Activite";

    }
    @Test
    public void TestEstuneActivite(){
        Activite toboggan = new Activite("Toboggan");
        Guichet guichet = new Guichet("guichet");

        assert toboggan.estUneActivite() : "Pb estUneActivite = activite";
        assert !toboggan.estUnGuichet() : "Pb estUnGuichet = activite";
        assert !guichet.estUneActivite() : "Pb estUneActivite = guichet";
        assert guichet.estUnGuichet() : "Pb estUnGuichet = guichet";
    }
    @Test
    public void TestGestionnaireAjouter() {
        GestionnairesEtapes gestionnaire = new GestionnairesEtapes();
        Activite toboggan = new Activite("Toboggan");
        Activite zoo = new Activite("zoo");
        Guichet guichet = new Guichet("guichet");

        gestionnaire.ajouter(zoo,toboggan,guichet);

        assert gestionnaire.nbEtapes()== 3 :"Probleme GestionnaireAjouter ";


    }
    @Test
    public void TestGetSuccesseur(){
        Etape e1 = new Activite("etape1") ;
        Etape e2 = new Activite("etape2") ;
        Etape e3 = new Activite("etape3") ;
        e1.ajouterSuccesseur(e2,e3);
        e1.getSuccesseur(1) ;
    }

}
