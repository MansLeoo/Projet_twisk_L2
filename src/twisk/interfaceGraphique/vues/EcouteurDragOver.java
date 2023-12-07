package twisk.interfaceGraphique.vues;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class EcouteurDragOver implements EventHandler<DragEvent> {
    @Override
    public void handle(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.MOVE) ;
    }
}
