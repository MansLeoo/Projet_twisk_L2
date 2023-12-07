package twisk.interfaceGraphique.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.interfaceGraphique.mondeIG.MondeIG;
import twisk.interfaceGraphique.mondeIG.PointDeControleIG;
import twisk.interfaceGraphique.mondeIG.TailleComposants;


public class VuePointDeControleIG extends Circle implements Observateur {
    private PointDeControleIG point ;
    private MondeIG monde ;
    public VuePointDeControleIG(PointDeControleIG p, MondeIG monde){
        this.point = p ;
        this.setCenterX(point.getPosX());
        this.setCenterY(point.getPosY());
        this.setRadius(TailleComposants.TAILLE_POINT);
        this.setStyle("-fx-fill:#EE00E3 "); ;
        if(!monde.isSimu()) {
            this.setOnMouseClicked(new EcouteurClique(monde, this.point));
        }
        this.reagir();
    }
    @Override
    public void reagir() {

    }
}
