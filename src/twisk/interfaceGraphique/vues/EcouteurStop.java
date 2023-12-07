package twisk.interfaceGraphique.vues;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.interfaceGraphique.mondeIG.MondeIG;
import twisk.outils.KitC;
import twisk.outils.ThreadsManager;

public class EcouteurStop implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        ThreadsManager.getInstance().detruireTout();
        this.monde.setSimu(false);
    }
    private MondeIG monde ;

    public EcouteurStop(MondeIG monde){
        this.monde = monde ;
    }
}
