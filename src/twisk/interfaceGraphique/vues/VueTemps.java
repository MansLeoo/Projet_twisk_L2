package twisk.interfaceGraphique.vues;

import javafx.scene.control.TextInputDialog;
import twisk.interfaceGraphique.exceptions.ValueException;
import twisk.interfaceGraphique.mondeIG.EtapeIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;

import java.util.Iterator;

public class VueTemps extends TextInputDialog implements Observateur{
    private MondeIG monde ;
    public VueTemps(MondeIG monde) throws ValueException {
        this.monde = monde ;
        Iterator<EtapeIG> iterator = this.monde.iteratorEtapeSelect() ;
        this.setTitle("Modifier le temps");
        this.setContentText("temps de l'activité :");
        this.showAndWait();
        String result = this.getResult() ;
        this.monde.modifierTemps(result);
    }
    @Override
    public void reagir() {

    }
}