package twisk.interfaceGraphique.vues;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.interfaceGraphique.mondeIG.ActiviteIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;

public class EcouteurBoutonActivite implements EventHandler<ActionEvent> {
    private MondeIG monde  ;
    public EcouteurBoutonActivite(MondeIG monde){
    this.monde = monde ;
    }


    public void handle(ActionEvent event) {
        this.monde.ajouter(new ActiviteIG(200,100,6,3));
        //this.monde.ajouter(new GuichetIG("test",200,70,3));

        monde.notifierObservateur();
    }

}
