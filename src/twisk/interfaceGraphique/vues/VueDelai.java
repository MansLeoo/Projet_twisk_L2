package twisk.interfaceGraphique.vues;

import javafx.scene.control.TextInputDialog;
import twisk.interfaceGraphique.exceptions.ValueException;
import twisk.interfaceGraphique.mondeIG.EtapeIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;


import java.util.Iterator;


public class VueDelai extends TextInputDialog implements Observateur{
    private MondeIG monde ;
    public VueDelai(MondeIG monde) throws ValueException {
        this.monde = monde ;
        Iterator<EtapeIG> iterator = this.monde.iteratorEtapeSelect() ;
        this.setTitle("Modifier le délai");
        this.setContentText("délai de l'activité :");
        this.showAndWait();
        String result = this.getResult() ;
        this.monde.modifierDelai(result);
    }
    @Override
    public void reagir() {

    }
}