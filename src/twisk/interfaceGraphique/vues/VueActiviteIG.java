package twisk.interfaceGraphique.vues;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import twisk.interfaceGraphique.mondeIG.ActiviteIG;
import twisk.interfaceGraphique.mondeIG.EtapeIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;
import twisk.interfaceGraphique.mondeIG.TailleComposants;


import java.util.Iterator;

public class VueActiviteIG extends VueEtapeIG implements Observateur{
    public VueActiviteIG(MondeIG monde, ActiviteIG activite) {

        super(monde, activite);
        this.setMinSize(activite.getLargeur(),activite.getHauteur());
        this.setMaxSize(activite.getLargeur(),activite.getHauteur());

        HBox box = new HBox();
        HBox hbox = new HBox() ;
        Label label = new Label(activite.getNom()+" | "+activite.getTemps()+" ± "+activite.getEcartTemps()) ;
        label.setStyle("-fx-padding: 0px 10px  0px 10px ;-fx-text-fill: #0059FF ;-fx-font-weight: bold;");
        //label.setAlignment(Pos.BASELINE_CENTER) ;
        hbox.getChildren().add(label) ;
        if (activite.isSortie()){
            // Chargez l'image à partir du fichier sur le disque
            Image image = new Image("twisk/ressources/img/sortie.png");
            // Redimensionnez l'image en créant une nouvelle instance de ImageView avec une nouvelle largeur et hauteur
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(15);
            imageView.setFitHeight(15);
            hbox.getChildren().add(imageView) ;
        }
        if (activite.isEntree()){
            // Chargez l'image à partir du fichier sur le disque
            Image image = new Image("twisk/ressources/img/entre.png");
            // Redimensionnez l'image en créant une nouvelle instance de ImageView avec une nouvelle largeur et hauteur
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(15);
            imageView.setFitHeight(15);
            hbox.getChildren().add(imageView) ;
        }

        this.getChildren().addAll(hbox,box) ;
        box.setStyle("-fx-background-color: linear-gradient(to top, #AFAFAF, #E4E1E1) ; -fx-border-color: #0059FF; ") ;
        box.setMinSize(TailleComposants.LARGEUR_ETAPE,TailleComposants.HAUTEUR_ETAPE);
        box.setMaxSize(TailleComposants.LARGEUR_ETAPE,TailleComposants.HAUTEUR_ETAPE);
        this.setStyle("-fx-padding: 10px 10px;-fx-background-radius: 5px;-fx-border-radius: 5px;-fx-border-color: #A200FF;-fx-background-color: #FFFFFF ;");
        if(!monde.isSimu()) {
            this.setOnMouseClicked(new EcouteurSelection(monde, activite));
        }
        Iterator<EtapeIG> iterator = monde.iteratorEtapeSelect() ;
        while (iterator.hasNext()){
            if (activite.getIdentifiant().equals(iterator.next().getIdentifiant())){
                this.setStyle("-fx-padding: 10px 10px;-fx-background-radius: 5px;-fx-border-radius: 5px;-fx-border-color: #A200FF;-fx-background-color: #C5C5C5 ;");

            }
        }
        this.setId(activite.getIdentifiant());
        if(!monde.isSimu()) {
            this.setOnDragDetected(new EcouteurDrag(this));
        }

    }
    @Override
    public void reagir() {

    }
}
