package twisk.interfaceGraphique.vues;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import twisk.interfaceGraphique.exceptions.ValueException;
import twisk.interfaceGraphique.mondeIG.MondeIG;
import twisk.monde.Monde;

public class EcouteurNbJetons implements EventHandler<ActionEvent> {
    private MondeIG monde ;
    public EcouteurNbJetons(MondeIG monde) {
        this.monde = monde ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            VueNbJetons vue = new VueNbJetons(this.monde) ;
        } catch (ValueException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Valeur Incorrect");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.play();
            pause.setOnFinished(new EcouteurAlert(alert));
            alert.showAndWait();
        }

    }
}
