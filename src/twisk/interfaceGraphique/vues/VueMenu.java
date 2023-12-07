package twisk.interfaceGraphique.vues;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import twisk.interfaceGraphique.mondeIG.MondeIG;

public class VueMenu extends MenuBar implements Observateur{

    private  MenuItem renommer;
    private MondeIG monde ;
    private  MenuItem delais ;
    private  MenuItem temps ;
    private MenuItem sortie ;
    private MenuItem entree ;
    private MenuItem supprimer ;
    private MenuItem delSelect ;
    private MenuItem jetons ;
    private MenuItem nbClient ;

    public VueMenu(MondeIG monde){
        super();
        this.monde = monde;

        Menu menu = new Menu("Fichier");
        Menu edition = new Menu("Edition") ;
        Menu menuMonde = new Menu("Monde");
        Menu param = new Menu("Paramètres");
        Menu clients = new Menu("Clients");


        this.nbClient = new MenuItem("Définir le nombre de clients");
        clients.getItems().add(this.nbClient) ;
        this.delais = new MenuItem("Modifier Delai") ;
        this.temps = new MenuItem("Modifier Temps") ;
        this.jetons = new MenuItem("Modifier le nombre de jetons") ;
        delais.setOnAction(new EcouteurDelai(this.monde));
        temps.setOnAction(new EcouteurTemps(this.monde));
        param.getItems().addAll(temps,delais,jetons) ;
        this.sortie = new MenuItem("Sortie") ;
        this.entree = new MenuItem("Entrée") ;
        sortie.setOnAction(new EcouteurSortie(this.monde));
        entree.setOnAction(new EcouteurEntree(this.monde));
        this.nbClient.setOnAction(new EcouteurClient(this.monde));
        menuMonde.getItems().addAll(sortie,entree) ;
        this.supprimer = new MenuItem("Supprimer ") ;
        MenuItem quitter = new MenuItem("Quitter ") ;
        this.delSelect  = new MenuItem("Effacer la sélection") ;
        this.renommer = new MenuItem("Renommer") ;
        supprimer.setAccelerator( KeyCombination.keyCombination("Ctrl+D"));
        quitter.setAccelerator( KeyCombination.keyCombination("Ctrl+Q"));
        this.delSelect.setAccelerator( KeyCombination.keyCombination("Ctrl+E"));
        this.delSelect.setOnAction(new EcouteurUnselect(this.monde));
        quitter.setOnAction(new EcouteurQuitter());
        supprimer.setOnAction(new EcouteurSupprimer(this.monde));
        renommer.setOnAction(new EcouteurRenommer(this.monde));
        jetons.setOnAction(new EcouteurNbJetons(this.monde));
        menu.getItems().addAll(quitter);
        edition.getItems().addAll(supprimer,delSelect,renommer) ;
        this.getMenus().addAll(menu,edition,menuMonde,param,clients) ;
    }
    @Override
    public void reagir() {
        if(this.monde.getNbActiviteSelect() != 1){
            this.delais.setDisable(true);
            this.temps.setDisable(true);

        } else  {
            this.delais.setDisable(false);
            this.temps.setDisable(false);

        }
        if (this.monde.getNbEtapeSelect() == 0 ){
            this.delais.setDisable(true);
            this.temps.setDisable(true);
            this.renommer.setDisable(true);
            this.sortie.setDisable(true);
            this.entree.setDisable(true);

        }
        else{
            this.sortie.setDisable(false);
            this.entree.setDisable(false);
            this.renommer.setDisable(false);
        }
        if(this.monde.getNbEtapeSelect() > 0 || this.monde.getNbArcSelect() > 0){
            this.delSelect.setDisable(false);
        }
        else{
            this.delSelect.setDisable(true);

        }
        if (this.monde.getNbGuichetSelect() == 1 && this.monde.getNbEtapeSelect() == 1){
            this.jetons.setDisable(false);
        }
        else{
            this.jetons.setDisable(true);
        }
        if(this.monde.getNbEtapeSelect() == 0 && this.monde.getNbArcSelect() == 0){
            this.supprimer.setDisable(true);
        }
        else{
            this.supprimer.setDisable(false);
        }
        if(monde.isSimu()){
            this.nbClient.setDisable(true);
        }
        else{
            this.nbClient.setDisable(false);

        }
    }
}
