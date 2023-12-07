package twisk.interfaceGraphique;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.interfaceGraphique.mondeIG.MondeIG;
import twisk.interfaceGraphique.mondeIG.TailleComposants;
import twisk.interfaceGraphique.vues.VueMenu;
import twisk.interfaceGraphique.vues.VueMondeIG;
import twisk.interfaceGraphique.vues.VueOutils;


import java.util.ArrayList;


public class Main extends Application  {

    @Override
    public void start(Stage primaryStage)  {

        MondeIG monde = new MondeIG() ;

        primaryStage.setTitle("TwiskIG");
        BorderPane root = new BorderPane();
        primaryStage.setScene(new Scene(root, TailleComposants.LARGEUR_ECRAN, TailleComposants.HAUTEUR_ECRAN));
        VueOutils outils = new VueOutils(monde) ;
        root.setBottom(outils);
        VueMondeIG mondeIG = new VueMondeIG(monde) ;
        VueMenu menu= new VueMenu(monde) ;
        root.setCenter(mondeIG);
        root.setTop(menu);
        monde.ajouterObservateur(mondeIG);
        monde.ajouterObservateur(outils);
        monde.ajouterObservateur(menu);
        monde.notifierObservateur();
        primaryStage.show();



    }

    public static void main(String[] args) {
        launch(args);

    }

}