package twisk.interfaceGraphique.vues;

import javafx.scene.control.TextInputDialog;
import twisk.interfaceGraphique.exceptions.ValueException;
import twisk.interfaceGraphique.mondeIG.EtapeIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;


import java.util.Iterator;


public class VueNbJetons extends TextInputDialog implements Observateur{
    private MondeIG monde ;
    public VueNbJetons(MondeIG monde) throws ValueException {
        this.monde = monde ;
        this.setTitle("Modifier le nombre de jetons");
        this.setContentText("Nombre de jetons du guichet :");
        this.showAndWait();
        String result = this.getResult() ;

        this.monde.modifierJetons(result);
    }
    @Override
    public void reagir() {

    }
}