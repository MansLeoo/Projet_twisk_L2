package twisk.interfaceGraphique.vues;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class EcouteurAlert implements EventHandler<ActionEvent> {
    private Alert alert ;
    public EcouteurAlert(Alert alert){
        this.alert = alert ;
    }
    @Override
    public void handle(ActionEvent event) {
        this.alert.close();
    }
}
