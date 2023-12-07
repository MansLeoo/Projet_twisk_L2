package twisk.interfaceGraphique.vues;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.interfaceGraphique.mondeIG.MondeIG;

public class EcouteurSortie implements EventHandler<ActionEvent> {

    private MondeIG monde ;
    public EcouteurSortie(MondeIG monde) {
        this.monde = monde ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.monde.ajouterSortie();
    }
}
