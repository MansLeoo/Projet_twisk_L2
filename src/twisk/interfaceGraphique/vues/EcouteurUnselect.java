package twisk.interfaceGraphique.vues;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.interfaceGraphique.mondeIG.MondeIG;

public class EcouteurUnselect implements EventHandler<ActionEvent> {
    private MondeIG monde;

    public EcouteurUnselect(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.monde.unselect() ;
    }
}