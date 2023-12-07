package twisk.interfaceGraphique.vues;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.interfaceGraphique.mondeIG.ActiviteIG;
import twisk.interfaceGraphique.mondeIG.GuichetIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;

public class EcouteurBoutonGuichet implements EventHandler<ActionEvent> {

    private MondeIG monde  ;
    public EcouteurBoutonGuichet(MondeIG monde){
        this.monde = monde ;
    }


    public void handle(ActionEvent event) {
        this.monde.ajouter(new GuichetIG(200,70,3));

        monde.notifierObservateur();
    }

}


