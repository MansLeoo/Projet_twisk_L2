package twisk.interfaceGraphique.vues;

import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import twisk.interfaceGraphique.exceptions.MondeException;
import twisk.interfaceGraphique.mondeIG.MondeIG;
import twisk.outils.ThreadsManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class EcouteurSimuler implements EventHandler<ActionEvent> {
    private MondeIG monde ;

public EcouteurSimuler(MondeIG monde){
    this.monde = monde ;
}
    @Override
    public void handle(ActionEvent actionEvent) {
                try {
                    this.monde.simuler();
                } catch (MondeException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur lors de la création du monde");
                    alert.setHeaderText(null);
                    alert.setContentText(e.getMessage());
                    PauseTransition pause = new PauseTransition(Duration.seconds(3));
                    pause.play();
                    pause.setOnFinished(new EcouteurAlert(alert));
                    alert.showAndWait();
                } catch (IOException e) {
                    throw new RuntimeException(e);

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
        }

    }

