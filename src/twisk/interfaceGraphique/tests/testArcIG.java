package twisk.interfaceGraphique.tests;

import javafx.scene.shape.Arc;
import twisk.interfaceGraphique.mondeIG.ActiviteIG;
import twisk.interfaceGraphique.mondeIG.ArcIG;
import twisk.interfaceGraphique.mondeIG.EtapeIG;
import twisk.interfaceGraphique.mondeIG.PointDeControleIG;


public class testArcIG {


    @org.junit.Test
    public void testArcIG(){
        EtapeIG etape = new ActiviteIG(100,100) ;
        PointDeControleIG p1 = new PointDeControleIG(etape,100,200) ;
        PointDeControleIG p2 = new PointDeControleIG(etape,200,300) ;

        ArcIG arc = new ArcIG(p1,p2) ;
        assert arc.getEndPointX() == 200 : "Err getEndPointX" ;
        assert arc.getStartPointX() == 100 : "Err getStartPointX";
        assert arc.getStartPointY() == 200 : "Err getStartPointY";
        assert arc.getEndPointY() == 300  : "Err getEndPointY";
        assert arc.getStartPoint().getID().equals(p1.getID()) : " Err getID" ;
        assert arc.getEndPoint().getID().equals(p2.getID())  : " Err getID";


    }
}
