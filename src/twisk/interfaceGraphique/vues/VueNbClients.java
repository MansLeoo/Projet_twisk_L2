package twisk.interfaceGraphique.vues;

import javafx.scene.control.TextInputDialog;
import twisk.interfaceGraphique.exceptions.ValueException;
import twisk.interfaceGraphique.mondeIG.MondeIG;

public class VueNbClients extends TextInputDialog implements Observateur {
    private MondeIG monde;

    public VueNbClients(MondeIG monde) throws NumberFormatException {
        this.monde = monde;
        this.setTitle("Modifier le nombre de clients");
        this.setContentText("Nombre de clients :");
        this.showAndWait();
        int result = Integer.parseInt(this.getResult());

        this.monde.setNbClients(result);
    }

    @Override
    public void reagir() {

    }

}