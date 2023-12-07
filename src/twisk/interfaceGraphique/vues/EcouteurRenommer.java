package twisk.interfaceGraphique.vues;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import twisk.interfaceGraphique.mondeIG.MondeIG;

public class EcouteurRenommer implements EventHandler<ActionEvent> {
    private MondeIG monde;

    public EcouteurRenommer(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            monde.verifRename() ;
            VueRenommer vue = new VueRenommer(this.monde) ;
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Renommage impossible");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre d'activités sélectionné est incorrect.");
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.play();
            pause.setOnFinished(new EcouteurAlert(alert));
            alert.showAndWait();
        }
    }
}
