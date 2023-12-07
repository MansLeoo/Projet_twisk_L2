package twisk.interfaceGraphique.vues;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import twisk.interfaceGraphique.mondeIG.ArcIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;
import twisk.interfaceGraphique.mondeIG.TailleComposants;


import java.util.Iterator;

import static java.lang.Math.*;


public class VueArcIG extends Pane implements Observateur{
    MondeIG monde  ;
    ArcIG arc ;
    Line line ;
    Polygon poly ;
    public VueArcIG(MondeIG monde ,ArcIG arc){
        this.arc = arc ;
        this.monde = monde ;
        this.minHeight(TailleComposants.HAUTEUR_MONDE) ;
        this.minWidth(TailleComposants.LARGEUR_MONDE );
        this.line = new Line() ;
        this.line.setStartX(arc.getStartPointX());
        this.line.setStartY(arc.getStartPointY());
        this.line.setEndX(arc.getEndPointX());
        this.line.setEndY(arc.getEndPointY());
        this.line.setStrokeWidth(TailleComposants.LARGEUR_FLECHE);
        this.line.setStyle("-fx-stroke:#EE00E3 ");
        this.getChildren().add(line) ;
        //Ajout du triangle
        double p1X = (double) this.arc.getEndPoint().getPosX();
        double p1Y = (double) this.arc.getEndPoint().getPosY();
        double p2X = (double) this.arc.getStartPoint().getPosX();
        double p2Y = (double) this.arc.getStartPoint().getPosY();

        double[] points = {p1X-20,p1Y-10,p1X,p1Y,p1X - 20,p1Y+10} ;
        this.poly = new Polygon(points) ;

        double centreX = (p1X-20+p1X+p1X-20) / 3 ;
        double centreY = (p1Y-10+p1Y+p1Y+10) / 3 ;
        // Calcul de l'angle du triangle
        double angle = atan2(p1Y - p2Y, p1X - p2X) ;
        angle = angle * 180 / 3.14 ;
        poly.setLayoutX(p1X - centreX);
        poly.setLayoutY(p1Y - centreY);
        poly.setRotate(angle);

        this.poly.setStyle("-fx-stroke:#EE00E3 ");
        this.getChildren().add(poly) ;
        if(!monde.isSimu()) {
            EcouteurSelection select = new EcouteurSelection(this.monde, this.arc);

            line.setOnMouseClicked(select);
        }
        Iterator<ArcIG> iterator = this.monde.iteratorArcSelect() ;
        while (iterator.hasNext()){
            if (this.arc == iterator.next()){
                this.line.setStyle("-fx-stroke:#C5C5C5 ");
                this.poly.setStyle("-fx-stroke:#C5C5C5 ");

            }
        }
    }

    @Override
    public void reagir() {
    }
}
