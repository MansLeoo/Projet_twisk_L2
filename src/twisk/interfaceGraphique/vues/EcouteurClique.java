package twisk.interfaceGraphique.vues;

import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import twisk.interfaceGraphique.exceptions.BiffurcationException;
import twisk.interfaceGraphique.exceptions.TwiskException;
import twisk.interfaceGraphique.mondeIG.MondeIG;
import twisk.interfaceGraphique.mondeIG.PointDeControleIG;


public class EcouteurClique implements EventHandler<MouseEvent> {
    private MondeIG monde ;
    private PointDeControleIG point ;
    public EcouteurClique(MondeIG monde, PointDeControleIG p){
        this.monde = monde ;
        this.point = p ;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            this.monde.select(this.point);
        } catch (TwiskException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Liaison non valide");
            alert.setHeaderText(null);
            alert.setContentText("Liaison non valide");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.play();
            pause.setOnFinished(new EcouteurAlert(alert));
            alert.showAndWait();
        }
        catch(BiffurcationException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Le monde ne gère pas les bifurcations.");
            alert.setHeaderText(null);
            alert.setContentText("Liaison non valide");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.play();
            pause.setOnFinished(new EcouteurAlert(alert));
            alert.showAndWait();
        }

    }
    }

