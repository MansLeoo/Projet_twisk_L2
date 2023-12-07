package twisk.interfaceGraphique.vues;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import twisk.interfaceGraphique.mondeIG.EtapeIG;
import twisk.interfaceGraphique.mondeIG.GuichetIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;
import twisk.interfaceGraphique.mondeIG.TailleComposants;

import java.util.Iterator;


public class VueGuichetIG extends VueEtapeIG implements Observateur {

    public VueGuichetIG(MondeIG monde, GuichetIG guichet) {
        super(monde, guichet);
        this.setMinSize(guichet.getLargeur(),guichet.getHauteur());
        this.setMaxSize(guichet.getLargeur(),guichet.getHauteur());
        HBox hbox = new HBox() ;
        GridPane grid = new GridPane() ;
        grid.setGridLinesVisible(false);
        Label nom = new Label(""+guichet.getNom() + " : " + guichet.getNbJetons() + " jetons" ) ;
        this.setStyle("-fx-padding: 10px 10px;-fx-background-radius: 5px;-fx-border-radius: 5px;-fx-border-color: #00FF36;-fx-background-color: #FFFFFF ;");
        nom.setStyle("-fx-padding: 0px 10px  0px 10px ;-fx-text-fill: #00FF36 ;-fx-font-weight: bold;");
        grid.setStyle("-fx-background-color: linear-gradient(to top, #AFAFAF, #E4E1E1) ");
        grid.setStyle("-fx-border-color: #00FF36;");
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(10);
        grid.setHgap(1);
        for (int i = 0; i < 10; i++) {
            Pane pane = new Pane() ;
            pane.minHeight(TailleComposants.HAUTEUR_GRID ) ;
            pane.minWidth(TailleComposants.LARGEUR_GRID ) ;
            pane.prefWidth(TailleComposants.LARGEUR_GRID ) ;
            pane.setPrefHeight(TailleComposants.HAUTEUR_GRID );
            pane.setStyle("-fx-background-color: linear-gradient(to top, #AFAFAF, #E4E1E1) ;-fx-border-color: #00FF36; ");
            grid.add(pane,i,0);
            grid.getColumnConstraints().add(columnConstraints) ;


        }
        if(!monde.isSimu()) {
            this.setOnMouseClicked(new EcouteurSelection(monde, guichet));
        }
        Iterator<EtapeIG> iterator = monde.iteratorEtapeSelect() ;
        while (iterator.hasNext()){
            if (guichet.getIdentifiant().equals(iterator.next().getIdentifiant())){
                this.setStyle("-fx-padding: 10px 10px;-fx-background-radius: 5px;-fx-border-radius: 5px;-fx-border-color: #A200FF;-fx-background-color: #C5C5C5 ;");

            }
        }

        if (guichet.isSortie()){
            // Chargez l'image à partir du fichier sur le disque
            Image image = new Image("twisk/ressources/img/sortie.png");
            // Redimensionnez l'image en créant une nouvelle instance de ImageView avec une nouvelle largeur et hauteur
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(15);
            imageView.setFitHeight(15);
            hbox.getChildren().add(imageView) ;
        }
        if (guichet.isEntree()){
            // Chargez l'image à partir du fichier sur le disque
            Image image = new Image("twisk/ressources/img/entre.png");
            // Redimensionnez l'image en créant une nouvelle instance de ImageView avec une nouvelle largeur et hauteur
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(15);
            imageView.setFitHeight(15);
            hbox.getChildren().add(imageView) ;
        }
        this.getChildren().addAll(hbox,nom,grid) ;
        this.setId(guichet.getIdentifiant());
        if(!monde.isSimu()) {
            this.setOnDragDetected(new EcouteurDrag(this));
        }
    }

}