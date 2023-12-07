package twisk.interfaceGraphique.vues;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import twisk.interfaceGraphique.mondeIG.MondeIG;

public class VueOutils extends TilePane implements Observateur {
    private MondeIG monde ;


    public VueOutils(MondeIG monde){
        this.monde = monde ;
        this.reagir();

    }

    @Override
    public void reagir() {
        VueOutils outils = this ;
        Runnable run = new Runnable() {
            @Override
            public void run() {


                outils.getChildren().clear();

                //Création du bouttons
                Button bouton = new Button("Ajouter activité");
                Button boutonGuichet = new Button("Ajouter guichet");
                Button boutonSimuler = new Button();
                Image img = new Image("twisk/ressources/img/play.png");

                if (monde.isSimu()) {
                    img = new Image("twisk/ressources/img/stop.png");
                }

                ImageView view = new ImageView(img);

                view.setFitWidth(20);
                view.setFitHeight(20);
                boutonSimuler.setGraphic(view);
                if (!outils.monde.isSimu()) {
                    boutonSimuler.setOnAction(new EcouteurSimuler(outils.monde));
                }
                outils.getChildren().addAll(bouton, boutonGuichet, boutonSimuler);
                bouton.setOnAction(new EcouteurBoutonActivite(monde));
                if (outils.monde.isSimu()) {
                    boutonSimuler.setOnAction(new EcouteurStop(monde));
                }
                bouton.setTooltip(new Tooltip("Ajouter une Activité"));


                boutonGuichet.setOnAction(new EcouteurBoutonGuichet(monde));
                boutonGuichet.setTooltip(new Tooltip("Ajouter un Guichet"));
                if (!outils.monde.isSimu()) {
                    boutonGuichet.setDisable(false);
                    bouton.setDisable(false);
                } else {
                    boutonGuichet.setDisable(true);
                    bouton.setDisable(true);
                }
            }
        };

        if(Platform.isFxApplicationThread()){
            run.run();
        }
        else{
            Platform.runLater(run);
        }
    }
}
