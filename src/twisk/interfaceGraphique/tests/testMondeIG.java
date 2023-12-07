package twisk.interfaceGraphique.tests;

import twisk.interfaceGraphique.mondeIG.ActiviteIG;
import twisk.interfaceGraphique.mondeIG.EtapeIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;

public class testMondeIG {
    @org.junit.Test
    public void testEtapeIG(){
        ActiviteIG act = new ActiviteIG("Nom",100,100,10,10) ;
        EtapeIG etape = new ActiviteIG("Nom",100,100,10,2) ;
        assert act.getHauteur() == 100  : "Err Hauteur";
        assert act.getLargeur() == 100  : "Err Largeur";
        assert act.getNom().equals("Nom")  : "Err Nom";
        assert act.getPosX() < 500 : "Err posX > 500 " ;
        assert act.getPosX() > 0 : "Err posX < 0 " ;
        assert act.getPosY() < 500 : "Err posY > 500 " ;
        assert act.getPosY() > 0 : "Err posY < 0 " ;
        assert act.getIdentifiant().equals("0") : " Err identifiant";

        assert etape.getHauteur() == 100  : "Err Hauteur";
        assert etape.getLargeur() == 100  : "Err Largeur";
        assert etape.getNom().equals("Nom")  : "Err Nom";
        assert etape.getPosX() < 500 : "Err posX > 500 " ;
        assert etape.getPosX() > 0 : "Err posX < 0 " ;
        assert etape.getPosY() < 500 : "Err posY > 500 " ;
        assert etape.getPosY() > 0 : "Err posY < 0 " ;
        assert etape.getIdentifiant().equals("1")  : " Err identifiant";

    }
    public void testVerifMonde(){
        MondeIG monde = new MondeIG();
        EtapeIG etape = new ActiviteIG("etape1",100,100,10,2) ;
        EtapeIG etape2 = new ActiviteIG("etape2",100,100,10,2) ;


    }

}
