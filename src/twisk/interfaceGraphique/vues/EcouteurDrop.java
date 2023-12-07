package twisk.interfaceGraphique.vues;

import com.sun.prism.PixelFormat;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import twisk.interfaceGraphique.mondeIG.EtapeIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;


public class EcouteurDrop implements EventHandler<DragEvent> {
    private MondeIG monde ;
    public EcouteurDrop(MondeIG monde){
        this.monde = monde ;
    }
    @Override
    public void handle(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        String id = db.getString() ;
        dragEvent.setDropCompleted(true);

        EtapeIG e = this.monde.getEtape(id) ;
        e.setPosY((int) dragEvent.getSceneY()-20);
        e.setPosX((int) dragEvent.getSceneX());
        this.monde.replaceEtape(e);
        dragEvent.consume();
    }
}
