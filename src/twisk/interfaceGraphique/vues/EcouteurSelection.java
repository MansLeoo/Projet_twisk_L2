package twisk.interfaceGraphique.vues;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.interfaceGraphique.mondeIG.ArcIG;
import twisk.interfaceGraphique.mondeIG.EtapeIG;
import twisk.interfaceGraphique.mondeIG.MondeIG;


public class EcouteurSelection implements EventHandler<MouseEvent>{
    private MondeIG monde ;
    private EtapeIG etape ;
    private ArcIG arc ;
    public EcouteurSelection(MondeIG monde, EtapeIG etape){
        this.monde = monde ;
        this.etape = etape ;
        this.arc = null ;
    }
    public EcouteurSelection(MondeIG monde, ArcIG arc){
        this.monde = monde ;
        this.arc = arc ;
        this.etape = null ;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        if (this.arc != null){
            this.monde.selectionner(this.arc);
        }
        if (this.etape != null){
            this.monde.selectionner(this.etape);
        }
    }
}
