package twisk.interfaceGraphique.vues;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import twisk.interfaceGraphique.mondeIG.EtapeIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;


import java.util.Iterator;

public abstract class VueEtapeIG extends VBox implements Observateur{
    private MondeIG monde ;
    private EtapeIG etape ;
    public VueEtapeIG(MondeIG monde, EtapeIG etape) {
        this.monde = monde;
        this.etape = etape;
    }
    @Override
    public void reagir() {

    }
}
