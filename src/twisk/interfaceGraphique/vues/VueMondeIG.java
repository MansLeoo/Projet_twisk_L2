package twisk.interfaceGraphique.vues;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.interfaceGraphique.mondeIG.*;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.simulation.Client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

public class VueMondeIG  extends Pane implements Observateur{
    private MondeIG monde ;
    private ArrayList<VuePointDeControleIG> lstPoint ;
    private ArrayList<VueEtapeIG> lstEtape ;
    private ArrayList<Circle> circles ;
    public VueMondeIG(MondeIG monde){
        this.monde = monde ;
        this.lstEtape = new ArrayList<>() ;
        this.lstPoint = new ArrayList<>() ;
        this.reagir();
        this.circles = genereCircle() ;
    }

    private ArrayList<Circle> genereCircle() {
        ArrayList<Circle> circles = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int color = random.nextInt(5);
            Circle circle = new Circle(5);
            switch (color) {
                case 0:
                    circle.setFill(Color.AQUA);
                    break;
                case 1:
                    circle.setFill(Color.GREEN);

                    break;
                case 2:
                    circle.setFill(Color.PURPLE);

                    break;
                case 3:
                    circle.setFill(Color.BROWN);
                    break;
                case 4:
                    circle.setFill(Color.YELLOW);
                    break;
            }
            circles.add(circle);
        }
        return circles;
    }

    @Override
    public void reagir() {
        VueMondeIG vue = this ;
        Runnable command = new Runnable() {
            @Override
            public void run() {

                vue.setStyle("-fx-background-color: #EDEDED ;");
                vue.clearObservateur();
                vue.getChildren().clear();


                //ajout des arcs
                Iterator<ArcIG> iteratorArc = vue.monde.iteratorArc();
                VueArcIG arc;
                while (iteratorArc.hasNext()) {
                    ArcIG next = iteratorArc.next();
                    arc = new VueArcIG(vue.monde, next);
                    vue.getChildren().add(arc);
                    arc.relocate(0, 0);
                }

                // Ajout des etapes
                EtapeIG etape;
                Iterator<Entry<String, EtapeIG>> iterator = vue.monde.iterator();
                while (iterator.hasNext()) {
                    etape = iterator.next().getValue();
                    if (etape.isActivite()) {
                        ActiviteIG activite = (ActiviteIG) etape;
                        VueEtapeIG vueE = new VueActiviteIG(monde, activite);
                        monde.ajouterObservateur(vueE);
                        vue.lstEtape.add(vueE);
                        vueE.relocate(activite.getPosX(), activite.getPosY());
                        vue.getChildren().add(vueE);
                    }
                    if (etape.isGuichet()) {
                        GuichetIG guichet = (GuichetIG) etape;
                        VueEtapeIG vueE = new VueGuichetIG(monde, guichet);
                        monde.ajouterObservateur(vueE);
                        vue.lstEtape.add(vueE);
                        vueE.relocate(guichet.getPosX(), guichet.getPosY());
                        vue.getChildren().add(vueE);
                    }


                    //Ajout des points de Controle
                    Iterator<PointDeControleIG> iterator2 = etape.iterator();
                    while (iterator2.hasNext()) {
                        VuePointDeControleIG vpc = new VuePointDeControleIG(iterator2.next(), vue.monde);
                        vue.getChildren().add(vpc);
                        monde.ajouterObservateur(vpc);
                        vue.lstPoint.add(vpc);
                    }
                }
                    //____________________________________________________
                    //Gestion des clients
                    if (vue.monde.getGestionnaireClients() != null) {
                        int f = 0;
                        Iterator<Client> ite = vue.monde.getGestionnaireClients().iterator();
                        while (ite.hasNext()) {
                            Client c = ite.next();
                            EtapeIG e = vue.monde.getCorrespondance().trouverEtapeIG(c.getEtape());
                            if (e != null) {
                                if (e.isActivite()) {
                                    ActiviteIG a = (ActiviteIG) e;
                                    Random random = new Random();
                                    int randomNumber = random.nextInt(35);
                                    int randomNumber2 = random.nextInt(100);
                                    int x = a.getPosX() + 45 + randomNumber2;
                                    int y = a.getPosY() + 35 + randomNumber;
                                    circles.get(f).setCenterX(x);
                                    circles.get(f).setCenterY(y);
                                    vue.getChildren().add(circles.get(f));
                                    f++;

                                }
                                if (e.isGuichet()) {
                                    GuichetIG a = (GuichetIG) e;
                                    int mult = c.getRang() ;
                                    if(mult > 8){
                                        mult = 8 ;
                                    }
                                    int x = a.getPosX() + 200 - ( 22 * mult);
                                    int y = a.getPosY() + 40 ;
                                    circles.get(f).setCenterX(x);
                                    circles.get(f).setCenterY(y);
                                    vue.getChildren().add(circles.get(f));
                                    f++;

                                }
                            }
                        }
                }
        //______________________________________________________
                if(!vue.monde.isSimu()) {
                    vue.setOnDragOver(new EcouteurDragOver());
                    vue.setOnDragDropped(new EcouteurDrop(vue.monde));
                }
            }
        };
        if(Platform.isFxApplicationThread()){
            command.run();
        }else{
            Platform.runLater(command);
        }
    }

    private void clearObservateur() {
        for (int i = 0; i < lstPoint.size() ; i++) {
            this.monde.supprimerObservateur(lstPoint.get(i));
        }
        for (int i = 0; i < lstEtape.size() ; i++) {
            this.monde.supprimerObservateur(lstEtape.get(i));
        }
    }
}
