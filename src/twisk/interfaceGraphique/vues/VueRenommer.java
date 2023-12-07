package twisk.interfaceGraphique.vues;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.interfaceGraphique.mondeIG.EtapeIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;


import java.util.Iterator;


public class VueRenommer extends TextInputDialog implements Observateur{
    private MondeIG monde ;
    public VueRenommer(MondeIG monde){
        this.monde = monde ;
        Iterator<EtapeIG> iterator = this.monde.iteratorEtapeSelect() ;
        this.setTitle("Renommer l'activité");
        this.setContentText("Nom de l'activité :");
        this.setHeaderText("Renommer :");
        this.showAndWait();
        String result = this.getResult() ;
        this.monde.renommer(result);
        this.monde.notifierObservateur();
    }
    @Override
    public void reagir() {

    }
}
