package twisk.interfaceGraphique.tests;

import twisk.interfaceGraphique.mondeIG.ActiviteIG;
import twisk.interfaceGraphique.mondeIG.EtapeIG;
import twisk.interfaceGraphique.mondeIG.PointDeControleIG;


import java.util.Iterator;

public class testPointIG {

    @org.junit.Test
    public void testPointIG() {
        EtapeIG etape = new ActiviteIG(100,100) ;
        PointDeControleIG p1 = new PointDeControleIG(etape,100,100) ;
        PointDeControleIG p2 = new PointDeControleIG(etape,200,100) ;
        PointDeControleIG p3 = new PointDeControleIG(etape,300,100) ;

        assert p1.getPosX() == 100  : "Err getPosX" ;
        assert p1.getPosY() == 100  : "Err getPosX" ;
        assert p1.getEtape().getIdentifiant().equals("0")  : "Err getIdentifiant" ;
        Iterator<PointDeControleIG> iterator = etape.iterator() ;
        int i = 0 ;
        while (iterator.hasNext()){
            i++ ;
            PointDeControleIG p = iterator.next() ;
            System.out.println(p.getPosX());
        }
        assert i == 4 : "Err iterator" ;
    }
}

