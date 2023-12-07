package twisk.interfaceGraphique.vues;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.interfaceGraphique.mondeIG.MondeIG;

public class EcouteurSupprimer implements EventHandler<ActionEvent> {
    private MondeIG monde ;

    public EcouteurSupprimer(MondeIG monde){
        this.monde = monde ;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        this.monde.deleteSelection();
    }
}
