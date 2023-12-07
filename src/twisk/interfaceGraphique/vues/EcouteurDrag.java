package twisk.interfaceGraphique.vues;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;


public class EcouteurDrag implements EventHandler<MouseEvent> {
    private VueEtapeIG vue ;
    public EcouteurDrag(VueEtapeIG vue){
        this.vue = vue ;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        Dragboard db = this.vue.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        WritableImage img = this.vue.snapshot(null,null) ;
        ImageView imageView = new ImageView() ;
        imageView.setImage(img);
        // Store node ID in order to know what is dragged.
        content.putString(this.vue.getId());
        WritableImage screenshot = this.vue.snapshot(null,null);
        ImageView print_node = new ImageView(screenshot);
        db.setContent(content);
        db.setDragView(img);
        mouseEvent.consume();
    }
}
